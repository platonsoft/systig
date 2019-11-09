import { Component, OnInit } from '@angular/core';
import * as Chartist from 'chartist';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'stg-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  public pieChartData: number[] = [21, 39, 10, 14, 16];

  constructor() {
  }
  ngOnInit() {}
}
