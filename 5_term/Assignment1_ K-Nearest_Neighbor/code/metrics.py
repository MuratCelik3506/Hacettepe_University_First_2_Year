import numpy as np

# there are functions that return the result of our model's predictions 

def accuracy(y_test, y_pred):         # for classification problems
    correct = (y_test == y_pred).sum()
    return (correct / len(y_test)) * 100

def MAE(y_test, y_pred):              # for regression problems
    return 1 / len(y_test) * np.sum(abs(y_test - y_pred))