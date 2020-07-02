# b21827263 Murat Celik
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt


# 1)You will read the dataset (csv file) with Pandas library
dataSet = pd.read_csv('./breast-cancer-wisconsin.csv')

#I converted the missing cells in the file to NaN
dataSet = dataSet.replace("?",np.nan)

sum = 0
# 3) You will have complete these missing values using one of the techniques that you learn in your class
def miss_value(data):
    for column in data:
        if type(column) == type(1.1):
            continue
        global sum
        sum += int(column)
    sum_size = data.size - data.isnull().sum()
    return sum / sum_size
dataSet= dataSet.fillna(value=round(miss_value(dataSet["Bare_Nuclei"])))



dataSet.set_index('Code_number',inplace=True)



dataSet["Bare_Nuclei"] = dataSet["Bare_Nuclei"].astype(object).astype(int)


# 2) You are also expected to split your dataset into 2 sets
dataSet_Set = dataSet.iloc[:,:9]
training_set = dataSet_Set.sample(frac=0.8,)
test_set = dataSet_Set.sample(frac=0.2)


# 4) You are also expected to visualize pairwise correlations of the attributes of training set
f = plt.figure(figsize=(19, 15))
plt.matshow(training_set.corr(),cmap='hot',interpolation='nearest', fignum=1)
plt.xticks(range(len(training_set.columns)), training_set.columns, fontsize=7, rotation=40)
plt.yticks(range(len(training_set.columns)), training_set.columns, fontsize=7 )
plt.colorbar()
for t in range(len(training_set.corr().columns)):
        for k in range(len(training_set.corr().columns)):
            text = plt.text(k, t, np.around(training_set.corr().iloc[t, k], decimals=3),
                           ha="center", va="center", color="#ffffff")
plt.show()



