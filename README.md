# nn_pulsar_classifier
This is a web app that scans labeled radio sources from a pulsar survey and learns to identify members from an evaluation set. The app uses a custom MLP NN written in java that scales up to any depth and can be arbitrarily wide. The evaluation datapoints are a subset of the training dataset. This is obviously not ideal and later iterations will use an evaluation set that does not contain members from the training set. The NN has an accuracy of 98% with only 2 layers so the extra depth available is redundant, but it was a fun exercise regardless and it was interesting to see how changing the depth influenced processing time. Weights are stored in a rank 3 tensor.

This Java web application was written with Eclipse IDE for Java Developers Version: 2019-03 (4.11.0) and Spring Tool Suite 4.

The dataset originated from: https://www.kaggle.com/pavanraj159/predicting-a-pulsar-star and was obtained in the High Time Resolution Universe Survey. The dataset was made available by:

Dr. Robert Lyon
University of Manchester 
School of Physics and Astronomy, 
Alan Turing Building 
Manchester M13 9PL 
United Kingdom 
robert.lyon '@' manchester.ac.uk
