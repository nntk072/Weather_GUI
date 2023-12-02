package fi.tuni.forecast;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ForecastController implements Initializable {

   @FXML
   private TextField latitudeInput;
   @FXML
   private TextField longitudeInput;
   @FXML
   private TextField placeInput;
   @FXML
   private ComboBox<String> dailySelectionComboBox;
   @FXML
   private ComboBox<String> days16SelectionComboBox;
   @FXML
   private ComboBox<String> yearlySelectionComboBox;
   @FXML
   private Label errorLabel;
   @FXML
   ImageView titleImage;
   @FXML
   TabPane forecastTabPane;

   // Create a list for saving the data from the API
   private List<ForecastHourlyModel> forecastHourlyData;
   private List<Forecast16DaysModel> forecast16DaysData;
   private List<ForecastYearlyModel> forecastYearlyData;
   private List<GeoCodingModel> geoCodingData;

   @FXML
   protected void drawLineGraph() {
      // // Create a new line chart
      // LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
      // lineChart.setTitle("Air pollution");
      // lineChart.setLegendVisible(false);

      // // Define the x and y axes
      // xAxis.setLabel("Time");
      // yAxis.setLabel("Air pollution");
      // // Define a series
      // XYChart.Series<String, Number> series = new XYChart.Series<>();
      // // Add data to the series
      // for (int i = 0; i < airPollutionData.size(); i++) {
      // series.getData().add(new XYChart.Data<>(airPollutionData.get(i).getTime(),
      // airPollutionData.get(i).getAirPollution()));
      // }
      // // Add the series to the line chart
      // lineChart.getData().add(series);
      // // Add the line chart to the pane
      // airPollutionLineChart.getChildren().add(lineChart);
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      // Initialize the list
      forecastHourlyData = new ArrayList<>();
      forecast16DaysData = new ArrayList<>();
      forecastYearlyData = new ArrayList<>();
      geoCodingData = new ArrayList<>();
      // Initialize the Image
      Image forecastIconImage = new Image("https://cdn0.iconfinder.com/data/icons/ikonate/48/line-chart-512.png");
      titleImage.setImage(forecastIconImage);

      // Initialize the error label
      errorLabel.setVisible(false);
      forecastTabPane.setVisible(false);

      // Disable the latitude and longitude input fields if the other one is filled
      // and in the reverse direction
      latitudeInput.textProperty().addListener((observable, oldValue, newValue) -> {
         if (!newValue.isEmpty()) {
            placeInput.setDisable(true);
         } else {
            if (longitudeInput.getText().isEmpty()) {
               placeInput.setDisable(false);
            }
         }
      });

      longitudeInput.textProperty().addListener((observable, oldValue, newValue) -> {
         if (!newValue.isEmpty()) {
            placeInput.setDisable(true);
         } else {
            if (latitudeInput.getText().isEmpty()) {
               placeInput.setDisable(false);
            }
         }
      });
      placeInput.textProperty().addListener((observable, oldValue, newValue) -> {
         if (!newValue.isEmpty()) {
            latitudeInput.setDisable(true);
            longitudeInput.setDisable(true);
         } else {
            latitudeInput.setDisable(false);
            longitudeInput.setDisable(false);
         }
      });

      dailySelectionComboBox.setItems(FXCollections.observableArrayList("Temperature", "Feels like", "Temp min",
            "Temp max", "Pressure", "Sea level", "Ground level", "Humidity", "Temp kf", "Weather ID", "Weather main",
            "Weather description", "Weather icon", "Clouds all", "Wind speed", "Wind degree", "Wind gust", "Visibility",
            "Pop", "Rain 1h", "Sys pod"));

      days16SelectionComboBox
            .setItems(FXCollections.observableArrayList("Temperature day", "Temperature night", "Temperature evening",
                  "Temperature morning", "Feels like day", "Feels like night", "Feels like evening",
                  "Feels like morning", "Pressure", "Humidity", "Wind speed",
                  "Wind degree", "Cloudiness", "Rain", "Sunrise", "Sunset"));

      yearlySelectionComboBox.setItems(FXCollections.observableArrayList("Record min temp", "Record max temp",
            "Average min temp", "Average max temp", "Median temp", "Mean temp", "P25 temp", "P75 temp", "St dev temp",
            "Num temp", "Min pressure", "Max pressure", "Median pressure", "Mean pressure", "P25 pressure",
            "P75 pressure", "St dev pressure", "Num pressure", "Min humidity", "Max humidity", "Median humidity",
            "Mean humidity", "P25 humidity", "P75 humidity", "St dev humidity", "Num humidity", "Min wind", "Max wind",
            "Median wind", "Mean wind", "P25 wind", "P75 wind", "St dev wind", "Num wind", "Min clouds", "Max clouds",
            "Median clouds", "Mean clouds", "P25 clouds", "P75 clouds", "St dev clouds", "Num clouds"));
      
         
   }

   // Function for loading HTML data from the given URL
   private HttpRequest createHttpRequest(String url) {
      HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
      return request;
   }

   private List<ForecastHourlyModel> fetchHourlyData(HttpResponse<String> forecastHourlyRequest) {
      String responseBody = forecastHourlyRequest.body();
      JSONObject json = new JSONObject(responseBody);

      JSONArray listForecastHourlyDataReturn = json.getJSONArray("list");

      JSONObject city = json.getJSONObject("city");
      String countryCode = city.getString("country");
      int population = city.getInt("population");
      int timezone = city.getInt("timezone");
      int sunrise = city.getInt("sunrise");
      int sunset = city.getInt("sunset");
      int cityID = city.getInt("id");
      String cityName = city.getString("name");

      JSONObject coord = city.getJSONObject("coord");
      double latitude = coord.getDouble("lat");
      double longitude = coord.getDouble("lon");
      for (int i = 0; i < listForecastHourlyDataReturn.length(); i++) {
         JSONObject dataToJson = listForecastHourlyDataReturn.getJSONObject(i);
         int timeStamp = dataToJson.getInt("dt");
         JSONObject main = dataToJson.getJSONObject("main");
         double temperature = main.getDouble("temp");
         double feelsLike = main.getDouble("feels_like");
         double tempMin = main.getDouble("temp_min");
         double tempMax = main.getDouble("temp_max");
         double pressure = main.getDouble("pressure");
         double seaLevel = main.getDouble("sea_level");
         double groundLevel = main.getDouble("grnd_level");
         double humidity = main.getDouble("humidity");
         double tempKf = main.getDouble("temp_kf");

         JSONArray weather = dataToJson.getJSONArray("weather");
         JSONObject weatherObject = weather.getJSONObject(0);
         int weatherID = weatherObject.getInt("id");
         String weatherMain = weatherObject.getString("main");
         String weatherDescription = weatherObject.getString("description");

         String weatherIcon = weatherObject.getString("icon");

         JSONObject clouds = dataToJson.getJSONObject("clouds");
         double cloudsAll = clouds.getDouble("all");

         JSONObject wind = dataToJson.getJSONObject("wind");
         double windSpeed = wind.getDouble("speed");
         double windDegree = wind.getDouble("deg");
         double windGust = wind.getDouble("gust");
         double visibility = dataToJson.getDouble("visibility");
         double pop = dataToJson.getDouble("pop");
         // System.out.println(timeStamp);
         double rain1h = 0;
         try {
            JSONObject rain = dataToJson.getJSONObject("rain");
            rain1h = rain.getDouble("1h");
         } catch (Exception e) {
         }
         JSONObject sys = dataToJson.getJSONObject("sys");
         String sysPod = sys.getString("pod");
         String dtTxt = dataToJson.getString("dt_txt");

         ForecastHourlyModel model = new ForecastHourlyModel(cityID, cityName, longitude, latitude, countryCode,
               population, timezone, sunrise, sunset, timeStamp, temperature, feelsLike, tempMin, tempMax, pressure,
               seaLevel, groundLevel, humidity, tempKf, weatherID, weatherMain, weatherDescription, weatherIcon,
               cloudsAll, windSpeed, windDegree, windGust, visibility, pop, rain1h, sysPod, dtTxt);
         forecastHourlyData.add(model);
      }
      return forecastHourlyData;
   }

   private List<Forecast16DaysModel> fetch16DaysData(HttpResponse<String> forecast16DaysRequest) {
      String responseBody = forecast16DaysRequest.body();
      JSONObject json = new JSONObject(responseBody);
      JSONObject city = json.getJSONObject("city");
      String cityName = city.getString("name");
      String countryCode = city.getString("country");
      int population = city.getInt("population");
      int timezone = city.getInt("timezone");
      JSONArray listForecast16DaysDataReturn = json.getJSONArray("list");

      for (int i = 0; i < listForecast16DaysDataReturn.length(); i++) {
         JSONObject dataToJson = listForecast16DaysDataReturn.getJSONObject(i);
         int timeStamp = dataToJson.getInt("dt");
         JSONObject temp = dataToJson.getJSONObject("temp");
         double temperatureDay = temp.getDouble("day");
         double temperatureNight = temp.getDouble("night");
         double temperatureEvening = temp.getDouble("eve");
         double temperatureMorning = temp.getDouble("morn");
         JSONObject feelsLike = dataToJson.getJSONObject("feels_like");
         double feelsLikeDay = feelsLike.getDouble("day");
         double feelsLikeNight = feelsLike.getDouble("night");
         double feelsLikeEvening = feelsLike.getDouble("eve");
         double feelsLikeMorning = feelsLike.getDouble("morn");
         double pressure = dataToJson.getDouble("pressure");
         double humidity = dataToJson.getDouble("humidity");
         double windSpeed = dataToJson.getDouble("speed");
         double windDegree = dataToJson.getDouble("deg");
         double cloudiness = dataToJson.getDouble("clouds");
         double rain = 0;
         try {
            rain = dataToJson.getDouble("rain");
         } catch (Exception e) {
         }
         int sunrise = dataToJson.getInt("sunrise");
         int sunset = dataToJson.getInt("sunset");

         Forecast16DaysModel model = new Forecast16DaysModel(cityName, longitudeInput.getText(),
               latitudeInput.getText(), countryCode, population, timezone, timeStamp, temperatureDay,
               temperatureNight, temperatureEvening, temperatureMorning, feelsLikeDay, feelsLikeNight,
               feelsLikeEvening, feelsLikeMorning, pressure, humidity, windSpeed, windDegree, cloudiness, rain,
               sunrise, sunset);
         forecast16DaysData.add(model);
      }
      return forecast16DaysData;

   }

   private List<ForecastYearlyModel> fetchYearlyData(HttpResponse<String> forecastYearlyRequest) {

      String responseBody = forecastYearlyRequest.body();
      JSONObject json = new JSONObject(responseBody);
      JSONArray listForecastYearlyDataReturn = json.getJSONArray("result");
      int cityID = json.getInt("city_id");
      for (int i = 0; i < listForecastYearlyDataReturn.length(); i++) {
         JSONObject dataToJson = listForecastYearlyDataReturn.getJSONObject(i);
         int month = dataToJson.getInt("month");
         int day = dataToJson.getInt("day");
         JSONObject temp = dataToJson.getJSONObject("temp");
         double recordMinTemp = temp.getDouble("record_min");
         double recordMaxTemp = temp.getDouble("record_max");
         double averageMinTemp = temp.getDouble("average_min");
         double averageMaxTemp = temp.getDouble("average_max");
         double medianTemp = temp.getDouble("median");
         double meanTemp = temp.getDouble("mean");
         double p25Temp = temp.getDouble("p25");
         double p75Temp = temp.getDouble("p75");
         double stDevTemp = temp.getDouble("st_dev");
         double numTemp = temp.getDouble("num");
         JSONObject pressure = dataToJson.getJSONObject("pressure");
         double minPressure = pressure.getDouble("min");
         double maxPressure = pressure.getDouble("max");
         double medianPressure = pressure.getDouble("median");
         double meanPressure = pressure.getDouble("mean");
         double p25Pressure = pressure.getDouble("p25");
         double p75Pressure = pressure.getDouble("p75");
         double stDevPressure = pressure.getDouble("st_dev");
         double numPressure = pressure.getDouble("num");
         JSONObject humidity = dataToJson.getJSONObject("humidity");
         double minHumidity = humidity.getDouble("min");
         double maxHumidity = humidity.getDouble("max");
         double medianHumidity = humidity.getDouble("median");
         double meanHumidity = humidity.getDouble("mean");
         double p25Humidity = humidity.getDouble("p25");
         double p75Humidity = humidity.getDouble("p75");
         double stDevHumidity = humidity.getDouble("st_dev");
         double numHumidity = humidity.getDouble("num");
         JSONObject wind = dataToJson.getJSONObject("wind");
         double minWind = wind.getDouble("min");
         double maxWind = wind.getDouble("max");
         double medianWind = wind.getDouble("median");
         double meanWind = wind.getDouble("mean");
         double p25Wind = wind.getDouble("p25");
         double p75Wind = wind.getDouble("p75");
         double stDevWind = wind.getDouble("st_dev");
         double numWind = wind.getDouble("num");
         JSONObject clouds = dataToJson.getJSONObject("clouds");
         double minClouds = clouds.getDouble("min");
         double maxClouds = clouds.getDouble("max");
         double medianClouds = clouds.getDouble("median");
         double meanClouds = clouds.getDouble("mean");
         double p25Clouds = clouds.getDouble("p25");
         double p75Clouds = clouds.getDouble("p75");
         double stDevClouds = clouds.getDouble("st_dev");
         double numClouds = clouds.getDouble("num");

         // Add to constructor
         double latitudeValue = Double.parseDouble(longitudeInput.getText());
         double longitudeValue = Double.parseDouble(latitudeInput.getText());
         ForecastYearlyModel model = new ForecastYearlyModel(cityID, latitudeValue, longitudeValue,
               month, day, recordMinTemp, recordMaxTemp, averageMinTemp, averageMaxTemp, medianTemp, meanTemp, p25Temp,
               p75Temp, stDevTemp, numTemp, minPressure, maxPressure, medianPressure, meanPressure, p25Pressure,
               p75Pressure, stDevPressure, numPressure, minHumidity, maxHumidity, medianHumidity, meanHumidity,
               p25Humidity, p75Humidity, stDevHumidity, numHumidity, minWind, maxWind, medianWind, meanWind, p25Wind,
               p75Wind, stDevWind, numWind, minClouds, maxClouds, medianClouds, meanClouds, p25Clouds, p75Clouds,
               stDevClouds, numClouds);
         forecastYearlyData.add(model);

      }
      return forecastYearlyData;
   }

   private List<GeoCodingModel> fetchGeoCodingData(HttpResponse<String> geoCodingRequest) {
      String responseBody = geoCodingRequest.body();
      JSONArray json = new JSONArray(responseBody);

      // Based on the comment above, extract cityName, country, latitude, longitude
      for (int i = 0; i < json.length(); i++) {
         JSONObject dataToJson = json.getJSONObject(i);
         String cityName = dataToJson.getString("name");
         String countryCode = dataToJson.getString("country");
         double latitude = dataToJson.getDouble("lat");
         double longitude = dataToJson.getDouble("lon");

         GeoCodingModel model = new GeoCodingModel(cityName, countryCode, latitude, longitude);
         geoCodingData.add(model);
      }
      return geoCodingData;
   }

   @FXML
   protected void getForecastData(ActionEvent event) {
      // Get the forecast data based on the user input (latitude, longitude)
      System.out.println("Get forecast data");
      String latitude = latitudeInput.getText();
      String longitude = longitudeInput.getText();
      String place = placeInput.getText();

      // Check if the input is valid
      if (latitude.isEmpty() || longitude.isEmpty()) {
         if (place.isEmpty()) {
            errorLabel.setVisible(true);
            errorLabel.setText("Please provide latitude and longitude or place!");
            return;
         }
      }

      if (latitude.isEmpty() || longitude.isEmpty()) {
         // Get the latitude and longitude based on the place
         String geoCodingURL = "https://api.openweathermap.org/geo/1.0/direct?q=" + place
               + "&limit=1&appid=cda257269cd8f052e74dc19afdd5252c";
         HttpRequest geoCodingRequest = createHttpRequest(geoCodingURL);
         try {
            errorLabel.setVisible(false);
            HttpResponse<String> geoCodingResponse = HttpClient.newHttpClient().send(geoCodingRequest,
                  HttpResponse.BodyHandlers.ofString());
            geoCodingData = fetchGeoCodingData(geoCodingResponse);
            latitude = String.valueOf(geoCodingData.get(0).getLatitude());
            longitude = String.valueOf(geoCodingData.get(0).getLongitude());
            // Set the latitude and longitude to the input fields
            latitudeInput.setText(latitude);
            longitudeInput.setText(longitude);
         } catch (IOException | InterruptedException e) {
            errorLabel.setText("Can not fetch weather!");
         }
      }

      try {
         double latitudeValue = Double.parseDouble(latitude);
         double longitudeValue = Double.parseDouble(longitude);

         // Print the latitude and longitude for debugging purposes
         System.out.println("Latitude: " + latitudeValue);
         System.out.println("Longitude: " + longitudeValue);
         System.out.println("Place: " + place);
         String geoCodingURL = "https://api.openweathermap.org/geo/1.0/reverse?lat=" + latitudeValue + "&lon="
               + longitudeValue + "&limit=1&appid=cda257269cd8f052e74dc19afdd5252c";

         HttpRequest geoCodingRequest = createHttpRequest(geoCodingURL);

         String forecastHourlyURL = "https://pro.openweathermap.org/data/2.5/forecast/hourly?lat=" + latitudeValue
               + "&lon="
               + longitudeValue + "&appid=cda257269cd8f052e74dc19afdd5252c";
         HttpRequest forecastHourlyRequest = createHttpRequest(forecastHourlyURL);

         String forecast16DaysURL = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + latitudeValue
               + "&lon="
               + longitudeValue + "&APPID=cda257269cd8f052e74dc19afdd5252c";
         HttpRequest forecast16DaysRequest = createHttpRequest(forecast16DaysURL);

         String forecastYearlyUrl = "http://history.openweathermap.org/data/2.5/aggregated/year?lat="
               + latitudeValue + "&lon="
               + longitudeValue + "&appid=cda257269cd8f052e74dc19afdd5252c";
         HttpRequest forecastYearlyRequest = createHttpRequest(forecastYearlyUrl);

         try {
            errorLabel.setVisible(false);

            // Get the data from the GeoCoding API
            HttpResponse<String> geoCodingResponse = HttpClient.newHttpClient().send(geoCodingRequest,
                  HttpResponse.BodyHandlers.ofString());
            geoCodingData = fetchGeoCodingData(geoCodingResponse);

            placeInput.setText(geoCodingData.get(0).getCityName() + ", " + geoCodingData.get(0).getCountry());

            // Get the data from the hourly forecast API
            HttpResponse<String> forecastHourlyResponse = HttpClient.newHttpClient().send(forecastHourlyRequest,
                  HttpResponse.BodyHandlers.ofString());
            forecastHourlyData = fetchHourlyData(forecastHourlyResponse);

            // Get the data from the 16-day forecast API
            HttpResponse<String> forecast16DaysResponse = HttpClient.newHttpClient().send(forecast16DaysRequest,
                  HttpResponse.BodyHandlers.ofString());
            forecast16DaysData = fetch16DaysData(forecast16DaysResponse);

            // Get the data from the yearly forecast API
            HttpResponse<String> forecastYearlyResponse = HttpClient.newHttpClient().send(forecastYearlyRequest,
                  HttpResponse.BodyHandlers.ofString());
            forecastYearlyData = fetchYearlyData(forecastYearlyResponse);

            // Display the data
            forecastTabPane.setVisible(true);
            drawLineGraph();
         } catch (IOException | InterruptedException e) {
            errorLabel.setText("Can not fetch weather!");
         }
      } catch (NumberFormatException e) {
         errorLabel.setVisible(true);
         errorLabel.setText("Latitude and longitude must be a number!");
      }

   }

   @FXML
   protected void resetForecastData(ActionEvent event) {
      // Reset the forecast data
      forecastHourlyData.clear();
      forecast16DaysData.clear();
      forecastYearlyData.clear();
      geoCodingData.clear();
      forecastTabPane.setVisible(false);

      // Reset the input fields
      latitudeInput.setText("");
      longitudeInput.setText("");
      placeInput.setText("");
      errorLabel.setVisible(false);
   }
}
