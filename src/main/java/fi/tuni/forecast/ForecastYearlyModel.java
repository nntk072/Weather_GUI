package fi.tuni.forecast;
import fi.tuni.function.Function;
public class ForecastYearlyModel {

    private int cityID;
    private double longtitude;
    private double latitude;
    private int month;
    private int day;
    private double recordMinTemp;
    private double recordMaxTemp;
    private double averageMinTemp;
    private double averageMaxTemp;
    private double medianTemp;
    private double meanTemp;
    private double p25Temp;
    private double p75Temp;
    private double stDevTemp;
    private double numTemp;

    private double minPressure;
    private double maxPressure;
    private double medianPressure;
    private double meanPressure;

    private double p25Pressure;
    private double p75Pressure;
    private double stDevPressure;
    private double numPressure;

    private double minHumidity;
    private double maxHumidity;
    private double medianHumidity;
    private double meanHumidity;
    private double p25Humidity;
    private double p75Humidity;
    private double stDevHumidity;
    private double numHumidity;

    private double minWind;
    private double maxWind;
    private double medianWind;
    private double meanWind;
    private double p25Wind;
    private double p75Wind;
    private double stDevWind;
    private double numWind;

    private double minClouds;
    private double maxClouds;
    private double medianClouds;
    private double meanClouds;
    private double p25Clouds;
    private double p75Clouds;
    private double stDevClouds;
    private double numClouds;

    public ForecastYearlyModel(int cityID, double longtitude, double latitude, int month, int day, double recordMinTemp, double recordMaxTemp, double averageMinTemp, double averageMaxTemp, double medianTemp, double meanTemp, double p25Temp, double p75Temp, double stDevTemp, double numTemp, double minPressure, double maxPressure, double medianPressure, double meanPressure, double p25Pressure, double p75Pressure, double stDevPressure, double numPressure, double minHumidity, double maxHumidity, double medianHumidity, double meanHumidity, double p25Humidity, double p75Humidity, double stDevHumidity, double numHumidity, double minWind, double maxWind, double medianWind, double meanWind, double p25Wind, double p75Wind, double stDevWind, double numWind, double minClouds, double maxClouds, double medianClouds, double meanClouds, double p25Clouds, double p75Clouds, double stDevClouds, double numClouds) {
        this.cityID = cityID;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.month = month;
        this.day = day;

        
        this.recordMinTemp = Function.convertKelvinToCelsius(recordMinTemp);
        this.recordMaxTemp = Function.convertKelvinToCelsius(recordMaxTemp);
        this.averageMinTemp = Function.convertKelvinToCelsius(averageMinTemp);
        this.averageMaxTemp = Function.convertKelvinToCelsius(averageMaxTemp);
        this.medianTemp = Function.convertKelvinToCelsius(medianTemp);
        this.meanTemp = Function.convertKelvinToCelsius(meanTemp);
        this.p25Temp = Function.convertKelvinToCelsius(p25Temp);
        this.p75Temp = Function.convertKelvinToCelsius(p75Temp);
        this.stDevTemp = Function.convertKelvinToCelsius(stDevTemp);
        this.numTemp = Function.convertKelvinToCelsius(numTemp);

        this.minPressure = minPressure;
        this.maxPressure = maxPressure;
        this.medianPressure = medianPressure;
        this.meanPressure = meanPressure;
        this.p25Pressure = p25Pressure;
        this.p75Pressure = p75Pressure;
        this.stDevPressure = stDevPressure;
        this.numPressure = numPressure;
        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;
        this.medianHumidity = medianHumidity;
        this.meanHumidity = meanHumidity;
        this.p25Humidity = p25Humidity;
        this.p75Humidity = p75Humidity;
        this.stDevHumidity = stDevHumidity;
        this.numHumidity = numHumidity;
        this.minWind = minWind;
        this.maxWind = maxWind;
        this.medianWind = medianWind;
        this.meanWind = meanWind;
        this.p25Wind = p25Wind;
        this.p75Wind = p75Wind;
        this.stDevWind = stDevWind;
        this.numWind = numWind;
        this.minClouds = minClouds;
        this.maxClouds = maxClouds;
        this.medianClouds = medianClouds;
        this.meanClouds = meanClouds;
        this.p25Clouds = p25Clouds;
        this.p75Clouds = p75Clouds;
        this.stDevClouds = stDevClouds;
        this.numClouds = numClouds;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getRecordMinTemp() {
        return recordMinTemp;
    }

    public void setRecordMinTemp(double recordMinTemp) {
        this.recordMinTemp = recordMinTemp;
    }

    public double getRecordMaxTemp() {
        return recordMaxTemp;
    }

    public void setRecordMaxTemp(double recordMaxTemp) {
        this.recordMaxTemp = recordMaxTemp;
    }

    public double getAverageMinTemp() {
        return averageMinTemp;
    }

    public void setAverageMinTemp(double averageMinTemp) {
        this.averageMinTemp = averageMinTemp;
    }

    public double getAverageMaxTemp() {
        return averageMaxTemp;
    }

    public void setAverageMaxTemp(double averageMaxTemp) {
        this.averageMaxTemp = averageMaxTemp;
    }

    public double getMedianTemp() {
        return medianTemp;
    }

    public void setMedianTemp(double medianTemp) {
        this.medianTemp = medianTemp;
    }

    public double getMeanTemp() {
        return meanTemp;
    }

    public void setMeanTemp(double meanTemp) {
        this.meanTemp = meanTemp;
    }

    public double getP25Temp() {
        return p25Temp;
    }

    public void setP25Temp(double p25Temp) {
        this.p25Temp = p25Temp;
    }

    public double getP75Temp() {
        return p75Temp;
    }

    public void setP75Temp(double p75Temp) {
        this.p75Temp = p75Temp;
    }

    public double getStDevTemp() {
        return stDevTemp;
    }

    public void setStDevTemp(double stDevTemp) {
        this.stDevTemp = stDevTemp;
    }

    public double getNumTemp() {
        return numTemp;
    }

    public void setNumTemp(double numTemp) {
        this.numTemp = numTemp;
    }

    public double getMinPressure() {
        return minPressure;
    }

    public void setMinPressure(double minPressure) {
        this.minPressure = minPressure;
    }

    public double getMaxPressure() {
        return maxPressure;
    }

    public void setMaxPressure(double maxPressure) {
        this.maxPressure = maxPressure;
    }

    public double getMedianPressure() {
        return medianPressure;
    }

    public void setMedianPressure(double medianPressure) {
        this.medianPressure = medianPressure;
    }

    public double getMeanPressure() {
        return meanPressure;
    }

    public void setMeanPressure(double meanPressure) {
        this.meanPressure = meanPressure;
    }

    public double getP25Pressure() {
        return p25Pressure;
    }

    public void setP25Pressure(double p25Pressure) {
        this.p25Pressure = p25Pressure;
    }

    public double getP75Pressure() {
        return p75Pressure;
    }

    public void setP75Pressure(double p75Pressure) {
        this.p75Pressure = p75Pressure;
    }

    public double getStDevPressure() {
        return stDevPressure;
    }

    public void setStDevPressure(double stDevPressure) {
        this.stDevPressure = stDevPressure;
    }

    public double getNumPressure() {
        return numPressure;
    }

    public void setNumPressure(double numPressure) {
        this.numPressure = numPressure;
    }

    public double getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(double minHumidity) {
        this.minHumidity = minHumidity;
    }

    public double getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(double maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public double getMedianHumidity() {
        return medianHumidity;
    }

    public void setMedianHumidity(double medianHumidity) {
        this.medianHumidity = medianHumidity;
    }

    public double getMeanHumidity() {
        return meanHumidity;
    }

    public void setMeanHumidity(double meanHumidity) {
        this.meanHumidity = meanHumidity;
    }

    public double getP25Humidity() {
        return p25Humidity;
    }

    public void setP25Humidity(double p25Humidity) {
        this.p25Humidity = p25Humidity;
    }

    public double getP75Humidity() {
        return p75Humidity;
    }

    public void setP75Humidity(double p75Humidity) {
        this.p75Humidity = p75Humidity;
    }

    public double getStDevHumidity() {
        return stDevHumidity;
    }

    public void setStDevHumidity(double stDevHumidity) {
        this.stDevHumidity = stDevHumidity;
    }

    public double getNumHumidity() {
        return numHumidity;
    }

    public void setNumHumidity(double numHumidity) {
        this.numHumidity = numHumidity;
    }

    public double getMinWind() {
        return minWind;
    }

    public void setMinWind(double minWind) {
        this.minWind = minWind;
    }

    public double getMaxWind() {
        return maxWind;
    }

    public void setMaxWind(double maxWind) {
        this.maxWind = maxWind;
    }

    public double getMedianWind() {
        return medianWind;
    }

    public void setMedianWind(double medianWind) {
        this.medianWind = medianWind;
    }

    public double getMeanWind() {
        return meanWind;
    }

    public void setMeanWind(double meanWind) {
        this.meanWind = meanWind;
    }

    public double getP25Wind() {
        return p25Wind;
    }

    public void setP25Wind(double p25Wind) {
        this.p25Wind = p25Wind;
    }

    public double getP75Wind() {
        return p75Wind;
    }

    public void setP75Wind(double p75Wind) {
        this.p75Wind = p75Wind;
    }

    public double getStDevWind() {
        return stDevWind;
    }

    public void setStDevWind(double stDevWind) {
        this.stDevWind = stDevWind;
    }

    public double getNumWind() {
        return numWind;
    }

    public void setNumWind(double numWind) {
        this.numWind = numWind;
    }

    public double getMinClouds() {
        return minClouds;
    }

    public void setMinClouds(double minClouds) {
        this.minClouds = minClouds;
    }

    public double getMaxClouds() {
        return maxClouds;
    }

    public void setMaxClouds(double maxClouds) {
        this.maxClouds = maxClouds;
    }

    public double getMedianClouds() {
        return medianClouds;
    }

    public void setMedianClouds(double medianClouds) {
        this.medianClouds = medianClouds;
    }

    public double getMeanClouds() {
        return meanClouds;
    }

    public void setMeanClouds(double meanClouds) {
        this.meanClouds = meanClouds;
    }

    public double getP25Clouds() {
        return p25Clouds;
    }

    public void setP25Clouds(double p25Clouds) {
        this.p25Clouds = p25Clouds;
    }

    public double getP75Clouds() {
        return p75Clouds;
    }

    public void setP75Clouds(double p75Clouds) {
        this.p75Clouds = p75Clouds;
    }

    public double getStDevClouds() {
        return stDevClouds;
    }

    public void setStDevClouds(double stDevClouds) {
        this.stDevClouds = stDevClouds;
    }

    public double getNumClouds() {
        return numClouds;
    }

    public void setNumClouds(double numClouds) {
        this.numClouds = numClouds;
    }


}
