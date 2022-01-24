import numpy as np
from code.metrics import accuracy, MAE

"""
    # There are functions where we separate and process our data. 
    
    train_test_split : It allows us to divide our data according to the desired ratio.
                       parameters : 
                            X : data feature,
                            y : data class,
                            rate : percentage of data split, default=0.8
                            
                            
                       return : X_train, X_test, y_train, y_test
    
    stratified_train_test_split : It allows us to divide our data classes according to their types. 
                                  It adds from each of our data classes in the train and test sections. 
                        parameters :
                            X : data feature,
                            y : data class, 
                            rate : percentage of data split 
                            
                       return : X_train, X_test, y_train, y_test
                                  
    cross_val_score : It divides our data into k folds and allows each fold to be used as test data. 
                       parameters : 
                            model : machine learning model,
                            X : data feature, 
                            y : data class,
                            k_split : number of folds, default = 5, 
                            scoring : accuracy technique, default='accuracy',
                            stratify : puts each class value in each fold, default=False
                            
                       return : accuracy value for each fold 

"""

def train_test_split(X, y, rate=0.8):
    idx = X.index.values.copy()
    np.random.seed(31)                         
    np.random.shuffle(idx)                           # shuffle to avoid data similarities 
    X, y = np.asarray(X.loc[idx]), np.asarray(y.loc[idx])     
    
    point = int(len(X) * 0.8)                        # divide data by desired ratio 
    
    return X[:point], X[point:], y[:point], y[point:]

def stratified_train_test_split(X, y, rate=0.8):   
    train_idx = np.zeros((0,), dtype=int)
    test_idx = np.zeros((0,), dtype=int)
    
    labels = y.unique()                              # save all labels types in data 
    for label in labels:
        idx = y[y == label].index.values.copy()      # find all index of specific label 
        np.random.seed(31)        
        np.random.shuffle(idx)                       # shuffle to avoid data similarities 
        train_idx = np.concatenate((train_idx, idx[:int(len(idx) * rate)]), axis=0)  # separate data into test and train 
        test_idx = np.concatenate((test_idx, idx[int(len(idx) * rate):]), axis=0)
        
    X, y = np.asarray(X), np.asarray(y)
    return X[train_idx], X[test_idx], y[train_idx], y[test_idx]


def cross_val_score(model, X, y, k_split = 5, stratify=False, scoring='accuracy'):   
    if stratify:                                     # If we want each label to be in each fold 
        labels = y.unique()
        folds_idx = [list() for i in range(k_split)]
        index = 0
        for label in labels:
            idx = y[y == label].index.values.copy()
            np.random.seed(31)
            np.random.shuffle(idx)
            for j in idx:
                folds_idx[index].append(j)
                index += 1
                if index == len(folds_idx):
                    index = 0 
                    
    else:                                            # divide our data by the desired number 
        idx = X.index.values.copy()
        np.random.seed(31)   
        np.random.shuffle(idx)
        folds_idx = np.split(idx, k_split) 
        
    scores = list()           
    for i in range(k_split):
        tmp = folds_idx.copy()
        test_ind = tmp.pop(i)
        train_ind = [ _ for sub in tmp for _ in sub]
        X_train, X_test, y_train, y_test = np.asarray(X.iloc[train_ind]), np.asarray(X.iloc[test_ind]), np.asarray(y[train_ind]), np.asarray(y[test_ind])
        model.fit_transform(X_train, y_train, X_test)# gives data to the model 
        y_pred = model.predict(X_test)               # output for test data by the model 
        
        if scoring == 'accuracy':
            score = accuracy(y_test, y_pred)
            
        elif scoring == 'MAE':
            score = MAE(y_test, y_pred)
            
        scores.append(score)                     # saves the accuracy result for each fold  
    
    scores = np.asarray(scores)                     
    scores = np.append(scores, [scores.min(), scores.mean(), scores.max()])
    return scores