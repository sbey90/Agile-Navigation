import { TaskService } from './../../../services/task.service';
import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';


@Component({
  selector: 'app-emp-dashboard',
  templateUrl: './emp-dashboard.component.html',
  styleUrls: ['./emp-dashboard.component.css']
})
export class EmpDashboardComponent implements OnInit {

  statusData: number[] = [0, 0, 0];
  priorityData: number[] = [0, 0, 0, 0];
  categoryData: number[] = [0, 0, 0, 0, 0, 0];
  constructor(private taskService: TaskService) { }

  ngOnInit(): void {

    this.taskService.getAllTasks()
      .subscribe(data => {

        data.forEach(obj => {

          if (obj.taskStatus === 'Pending') {
            this.statusData[0]++;
            console.log(this.statusData[0])
          } else if (obj.taskStatus === 'In Progress') {
            this.statusData[1]++;
            console.log(this.statusData[1])
          } else if (obj.taskStatus === 'Completed') {
            this.statusData[2]++;
            console.log(this.statusData[2])
          }

          if (obj.taskPriority === 'Do Now') {
            this.priorityData[0]++;
          } else if (obj.taskPriority === 'Do Next') {
            this.priorityData[1]++;
          } else if (obj.taskPriority === 'Do Last') {
            this.priorityData[2]++;
          } else if (obj.taskPriority === 'Do Never') {
            this.priorityData[3]++;
          }


          if (obj.taskCategory === 'Planning') {
            this.categoryData[0]++;
          } else if (obj.taskCategory === 'Analysis') {
            this.categoryData[1]++;
          } else if (obj.taskCategory === 'Design') {
            this.categoryData[2]++;
          } else if (obj.taskCategory === 'Development') {
            this.categoryData[3]++;
          } else if (obj.taskCategory === 'Testing') {
            this.categoryData[4]++;
          } else if (obj.taskCategory === 'Implementation') {
            this.categoryData[5]++;
          }


        })

        this.statusChart();
        this.priorityChart();
        this.categoryChart();
      })

  }


  statusChart() {

    var myChart = new Chart("myChart", {
      type: 'bar',
      data: {
        labels: ['Pending', 'In Progress', 'Completed'],
        datasets: [{
          label: '# of Task Status',
          data: this.statusData,
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(255, 159, 64, 0.2)'
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        title: {
          text: "Task Status Report",
          display: true
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    });


  }


  priorityChart() {

    const myChart = new Chart("myChartTwo", {
      type: 'doughnut',
      data: {
        labels: ['Do Now', 'Do Next', 'Do Last', 'Do Never'],
        datasets: [{
          label: '# of Task Priority',
          data: this.priorityData,
          backgroundColor: [
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(255, 99, 132, 0.2)'
          ],
          borderColor: [
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(255, 99, 132, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        title: {
          text: "Task Priority Report",
          display: true
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    });

  }

  categoryChart() {

    var myChart = new Chart("myChartThree", {
      type: 'bar',
      data: {
        labels: ['Planning', 'Analysis', 'Design', 'Development', 'Testing', 'Implementation'],
        datasets: [{
          label: '# of Task Category',
          data: this.categoryData,
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(255, 159, 64, 0.2)'
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        title: {
          text: "Task Category Report",
          display: true
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    });

  }

}
