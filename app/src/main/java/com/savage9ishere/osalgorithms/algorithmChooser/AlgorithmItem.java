package com.savage9ishere.osalgorithms.algorithmChooser;

public class AlgorithmItem {
     private String algoName;
     private String shortDescription;
     private String LongDescription;

     public AlgorithmItem(String algoName, String shortDescription, String longDescription) {
          this.algoName = algoName;
          this.shortDescription = shortDescription;
          LongDescription = longDescription;
     }

     public String getAlgoName() {
          return algoName;
     }

     public void setAlgoName(String algoName) {
          this.algoName = algoName;
     }

     public String getShortDescription() {
          return shortDescription;
     }

     public void setShortDescription(String shortDescription) {
          this.shortDescription = shortDescription;
     }

     public String getLongDescription() {
          return LongDescription;
     }

     public void setLongDescription(String longDescription) {
          LongDescription = longDescription;
     }
}
