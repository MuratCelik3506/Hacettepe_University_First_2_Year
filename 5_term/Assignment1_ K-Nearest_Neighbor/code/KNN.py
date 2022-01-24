"""
    Hacettepe University Computer Science 10/2021
    
    21827957 Humeyra Ucar
    21827263 Murat Celik

"""
import numpy as np

class KNNeighbors:
    """
    Parameters
    ----------
    model_type : {'Classification', 'Regression'}, default='Classification'

    n_neighbors : int, default=5
        
    weights : {'uniform', 'distance'},  default='uniform'
    
    
    Attributes
    ----------
    n_neighbors : number of neighbors investigated 
    
    model_type : determines which machine learning technique we will use based on the data output 
                {'Classification', 'Regression'}
                
    y_train : labels of our train data
    
    dist_train_test = distance of each test data from all train test data. rows show our test data, columns show our train data. 
    
    weights = holds whether the output of our test data is weighted or uniform 
      
    """
    n_neighbors = None
    model_type = None
    y_train = None
    dist_train_test = None
    weights = None

    # constructor function of KNN
    def __init__(self, n_neighbors=5, model_type='Classification', weights='uniform'):
        self.n_neighbors = n_neighbors
        self.model_type = model_type
        self.weights = weights

    # calculate distance of test data from train data 
    def fit_transform(self, X_train, y_train, X_test):
        self.y_train = y_train

        # initialize the matrix with (test size x train size) sizes.
        self.dist_train_test = np.zeros((X_test.shape[0], X_train.shape[0]))
        for rowidx in range(len(X_test)):
            dist = distance(X_train, X_test[rowidx])
            dist_row = dist.reshape(1, X_train.shape[0])  # reshaping for row vector.
            self.dist_train_test[rowidx] = dist_row       # add a row of test data  
        return True

    def predict(self, X_test):
        y_pred = list()
        
        for rowidx in range(len(self.dist_train_test)):
            row = self.dist_train_test[rowidx]
            idx = np.argsort(row)[:self.n_neighbors]
            
            # weightless/uniform KNN  
            if self.weights == 'uniform':                               
                if self.model_type == 'Classification':   # the mode of the labels of the nearest neighbors 
                    vals, counts = np.unique(self.y_train[idx], return_counts=True)
                    index = np.argmax(counts)
                    target_idx = vals[index]
                elif self.model_type == 'Regression':     # the mean of the labels of the nearest neighbors 
                    target_idx = np.mean(self.y_train[idx])

            # weighted/distance KNN
            elif self.weights == 'distance':              
                if self.model_type == 'Classification':   # weighted output according to distance of point 
                    target_distance = dict()
                    for label in np.unique(self.y_train):
                        target_distance[label] = 0
                    zero_checker = True
                    for dist, ind in zip(row[idx], idx):
                        if dist == 0:
                            target_idx = self.y_train[ind]
                            zero_checker = False
                            break
                        target_distance[self.y_train[ind]] += 1 / dist
                    if zero_checker:                      # if the distance of the point is 0 then its label is the same 
                        target_idx = max(target_distance, key=target_distance.get)

                elif self.model_type == 'Regression':     # weighted output according to distance of point
                    zero_checker = True
                    for ind in idx:
                        if (row[ind] == 0):
                            target_idx = self.y_train[ind]
                            zero_checker = False
                            break
                    if zero_checker:                      # if the distance of the point is 0 then its label is the same
                        total = 1 / np.sum(1 / row[idx])
                        target_idx = np.sum(self.y_train[idx] * total / row[idx])

            y_pred.append(target_idx)
        return y_pred                                     # return predicted value

# Euclidean distance for KNN algorithm
def distance(x1, x2):                                    
    return np.sqrt(np.sum((x1 - x2)**2, axis=1))