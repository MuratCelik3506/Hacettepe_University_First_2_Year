import pandas as pd
import numpy as np
import matplotlib
import matplotlib.pyplot as plt

def sigmoid(x):
    return 1 / (1 + np.exp(-0.005*x))

def sigmoid_derivative(x):
    return 0.005 * x * (1 - x )


def read_and_divide_into_train_and_test(csv_file):
    data = pd.read_csv(csv_file)
    data = data.replace("?", np.nan).dropna().set_index("Code_number")
    data["Bare_Nuclei"] = data["Bare_Nuclei"].astype(object).astype(int)

    train_set = data.iloc[:520, :9]
    train_labels_set = data.iloc[:520, 9:]
    train_inputs = train_set.to_numpy()
    train_labels = train_labels_set.to_numpy()

    test_set = data.iloc[520:, :9]
    test_labels_set = data.iloc[520:, 9:]
    test_inputs = test_set.to_numpy()
    test_labels = test_labels_set.to_numpy()

    return data , train_set , train_inputs, train_labels, test_inputs, test_labels

def run_on_test_set(test_inputs, test_labels, weights):
    test_output = sigmoid(np.dot(test_inputs, weights))
    test_predictions = list()
    for i in test_output:
        if i > 0.5:
            i = 1
            test_predictions.append([i])
        else:
            i = 0
            test_predictions.append([i])
    test_predictions = np.array(test_predictions)

    tp = 0
    for i in range(len(test_predictions)):
        if test_predictions[i] == test_labels[i]:
            tp += 1

    accuracy = tp / len(test_labels)
    return accuracy

def loss(train_inputs , train_labels, weights):
    outputs = np.dot(train_inputs, weights)
    outputs = sigmoid(outputs)
    loss = train_labels - outputs
    loss = np.mean(loss)
    return loss

def updated_weight(train_inputs , train_labels , weights):
    outputs = np.dot(train_inputs, weights)
    outputs = sigmoid(outputs)
    loss = train_labels - outputs
    tuning = loss * sigmoid_derivative(outputs)
    weights += np.dot(np.transpose(train_inputs), tuning)
    return weights


csv_file = './breast-cancer-wisconsin.csv'
iteration_count = 2500
np.random.seed(1)
weights = 2 * np.random.random((9, 1)) - 1
accuracy_array = []
loss_array = []
data , train_set , train_inputs, train_labels, test_inputs, test_labels = read_and_divide_into_train_and_test(csv_file)

for iteration in range(iteration_count):
    accuracy_array.append(run_on_test_set(test_inputs, test_labels, weights))
    updated_weight(train_inputs, train_labels, weights)
    loss_array.append(loss(train_inputs , train_labels, weights))

accuracy_array = np.array(accuracy_array)
loss_array = np.array(loss_array)

data_set = data.iloc[:,:9]
corr = data_set.corr()
fig = plt.figure(figsize=(10,10))
plt.matshow(corr , fignum=1 ,  cmap='gist_heat')
plt.xticks(range(len(corr.columns)), corr.columns, rotation = 30 )
plt.yticks(range(len(corr.columns)), corr.columns)
for i in range(len(corr.columns)):
        for j in range(len(corr.columns)):
            text = plt.text(j, i, np.around(corr.iloc[i, j], decimals=2),
                           ha="center", va="center", color="w")
plt.colorbar()
plt.show()


fig2, axs = plt.subplots(2, 1, constrained_layout=True)
axs[0].plot(np.arange(iteration_count) , accuracy_array)
axs[0].set_title('Accuracy Graph')
axs[0].set_xlabel('Iteration')
axs[0].set_ylabel('Accuracy')

axs[1].plot(np.arange(iteration_count) , loss_array)
axs[1].set_title('Loss Graph')
axs[1].set_xlabel('Iteration')
axs[1].set_ylabel('Loss')
plt.show()

