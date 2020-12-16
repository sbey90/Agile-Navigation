import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-weather-forecast',
  templateUrl: './weather-forecast.component.html',
  styleUrls: ['./weather-forecast.component.css']
})
export class WeatherForecastComponent implements OnInit {

  Forecast: any[];

  constructor() { }

  ngOnInit(): void {

    this.Forecast = [];

    this.getWeatherForecast();
  }

  getWeatherForecast() {
    fetch('https://api.openweathermap.org/data/2.5/onecall?lat=38.944122&lon=-77.356239&exclude=current,minutely,hourly&units=imperial&appid=bf83a092696e8fede4e3838d1ed856d8')
    .then(response=>response.json())
    .then(data=>{this.setWeatherForecast(data)})
  }

  setWeatherForecast(data: any) {
    //this.Forecast = data;
    console.log(data);
    //console.log(this.Forecast)
    let now = new Date();
    console.log(now.getDate() + 2);
    for (let i = 1; i < 6; i++) {
      //console.log(data.daily[i]);
      this.Forecast.push(data.daily[i]);
      this.Forecast[i-1].date = new Date();
      this.Forecast[i-1].date.setDate(now.getDate()+i);
      this.Forecast[i-1].date = this.Forecast[i-1].date.toString()
      this.Forecast[i-1].url = `http://openweathermap.org/img/wn/${data.daily[i].weather[0].icon}@2x.png`;
      console.log(this.Forecast[i-1])
      //this.configureDaily(i);
    }
  }

}
