import * as React from 'react';

export default class App extends React.Component {

  componentDidMount() {
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: ['11/01', '12/01', '13/01', '14/01', '15/01', '16/01'],
        datasets: [{
            label: 'MAX Temperature',
            data: [20, 22, 23, 25, 18, 20],
            borderColor: 'rgba(252, 60, 60, 1)',
            fill: false,
            borderWidth: 3
          },
          {
            label: 'MIN Temperature',
            data: [12, 13, 12, 15, 8, 10],
            borderColor: 'rgba(0, 75, 168, 1)',
            fill: false,
            borderWidth: 3
          }
        ]
      },
      options: {
        elements: {
          line: {
            tension: 0
          }
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        },
        legend: {
          labels: {
            boxWidth: 1,
          },
          position: 'right'
        },
        animation: {
          duration: 2000,
          easing: 'easeOutQuad'
        }
      }
    });
  }


  render() {
    return (
      <div className="chart-container" style={{width: '90%', height: '90%'}}>
        <canvas id="myChart"></canvas>
      </div>
    )
  }
}