import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-weather-current',
  templateUrl: './weather-current.component.html',
  styleUrls: ['./weather-current.component.css']
})
export class WeatherCurrentComponent implements OnInit {

  WeatherData: any;

  date: string;
  d: Date;
  http: HttpClient;
  constructor() { }

  ngOnInit(): void {
    this.WeatherData = {
      main : {},
      isDay: true,
      url: ''
    }
    
    this.d = new Date();
    this.date = this.d.toString();

    this.getWeatherData();
    
  }

  getWeatherData() {
    //let data = JSON.parse(this.http.get<any>(``));
    fetch('https://api.openweathermap.org/data/2.5/weather?q=Reston&appid=bf83a092696e8fede4e3838d1ed856d8')
    .then(response=>response.json())
    .then(data=>{this.setWeatherData(data)})
    // let data = JSON.parse('');
    // this.setWeatherData(data);
  }

  setWeatherData(data: any) {
    this.WeatherData = data;
    console.log(data);
    console.log(data.weather[0].icon)
    let sunsetTime = new Date(this.WeatherData.sys.sunset * 1000);
    let currentDate = new Date();
    this.WeatherData.url = `http://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png`;
    this.WeatherData.description = this.WeatherData.weather[0].description;
    this.WeatherData.isDay = (currentDate.getTime() < sunsetTime.getTime());
    this.WeatherData.temp_faren = ((this.WeatherData.main.temp - 273.25)*(9/5) + 32).toFixed(0);
    this.WeatherData.temp_min = ((this.WeatherData.main.temp_min - 273.25)*(9/5) + 32).toFixed(0);
    this.WeatherData.temp_max = ((this.WeatherData.main.temp_max - 273.25)*(9/5) + 32).toFixed(0);
    this.WeatherData.temp_feels_like = ((this.WeatherData.main.feels_like - 273.25)*(9/5) + 32).toFixed(0);
  }

}
