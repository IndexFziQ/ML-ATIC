# ML-ATIC
Abnormal Traffic Identification Classifier based on machine learning methods. My code for undergraduate graduation design.

## Requirements

1. Java SE 7

2. Jars in Maylib

3. Data from KDDCUP99, I use 10% version limited by the computing resource.

- [corrected.gz](https://github.com/IndexFziQ/ML-ATIC/blob/master/corrected.gz) 
- [kddcup.data.gz](https://github.com/IndexFziQ/ML-ATIC/blob/master/kddcup.data.gz) 
- [kddcup.data_10_percent.gz](https://github.com/IndexFziQ/ML-ATIC/blob/master/kddcup.data_10_percent.gz) 
- [kddcup.newtestdata_10_percent_unlabeled.gz](https://github.com/IndexFziQ/ML-ATIC/blob/master/kddcup.newtestdata_10_percent_unlabeled.gz) 
- [kddcup.testdata.unlabeled.gz](https://github.com/IndexFziQ/ML-ATIC/blob/master/kddcup.testdata.unlabeled.gz)
- [kddcup.testdata.unlabeled_10_percent.gz](https://github.com/IndexFziQ/ML-ATIC/blob/master/kddcup.testdata.unlabeled_10_percent.gz)   

## Install

1. Unzip TrainAndTest.zip and Model.zip in data file. Train.arff and Test.arff had been preprocessed by adding the head for raw data. If interested, you can open it and then explore.
2. There are some character encoding problems in java files which are UTF-8 and GB18030. And it may lead some mistakes in annotations.
3. Run MainContainer.java. It is a demo UI used for show the prediction.
4. Tips: test.txt is some examples from kddcup.newtestdata_10_percent_unlabeled.gz.

##License

MIT License

Copyright (c) 2019 Yuqiang Xie