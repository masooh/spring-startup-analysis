package com.github.lwaddicor.springstartupanalysis.dto;

import java.util.List;

public class Data {
    List<Dataset> datasets;
    List<String> labels;

    public List<Dataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public static class Dataset {
        List<Long> data;
        List<String> backgroundColor;

        public List<Long> getData() {
            return data;
        }

        public void setData(List<Long> data) {
            this.data = data;
        }

        public List<String> getBackgroundColor() {
            return backgroundColor;
        }

        public void setBackgroundColor(List<String> backgroundColor) {
            this.backgroundColor = backgroundColor;
        }
    }
}
