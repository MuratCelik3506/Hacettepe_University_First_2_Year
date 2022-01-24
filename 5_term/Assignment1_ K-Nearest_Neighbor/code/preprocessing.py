import numpy as np
"""
    Balances the range of values for each feature of our data 

    Parameters
    ----------
    X : data
    
    norm : {'MinMax', 'l1', 'l2', 'Max', 'StdScaler'}, default='MinMax'
    
    
    Returns
    -------
    X_normalized : normalized data     
    
"""

def normalize(X, norm='MinMax'):
    if norm == 'MinMax':
        return (X - X.min()) / (X.max() - X.min())
    elif norm == 'l1':
        return X / np.sum(abs(X))
    elif norm == 'l2':
        return X / np.sqrt(np.sum(X**2))
    elif norm == 'Max':
        return X / np.max(X)
    elif norm == 'StdScaler':
        return (X - np.mean(X)) / np.std(X)