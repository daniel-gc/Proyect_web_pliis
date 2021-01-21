import {Component, ViewChild, AfterViewInit} from '@angular/core';
import {AppMainComponent} from './app.main.component';
import {ScrollPanel} from 'primeng/scrollpanel';

@Component({
    selector: 'app-rightpanel',
    template: `
        <div class="layout-rightpanel" [ngClass]="{'layout-rightpanel-active': app.rightPanelActive}" (click)="app.onRightPanelClick()">
            <p-scrollPanel #scrollRightPanel [style]="{height: '100%'}">
              <div class="layout-rightpanel-wrapper">
                <div class="layout-rightpanel-header">
                    <div class="weather-day">Lunes</div>
                    <div class="weather-date">Octubre 21, 2019</div>
                </div>

                <div class="layout-rightpanel-content">
                    <h1>Clima</h1>
                    <h2>Ciudad de México. MX</h2>

                    <div class="weather-today">
                        <span class="weather-today-value">21&#8451;</span>
                        <img src="assets/layout/images/dashboard/weather-icon-2.svg" width="60"/>
                    </div>

                    <ul class="weekly-weather">
                        <li>
                            Martes
                            <img src="assets/layout/images/dashboard/weather-icon-1.svg"/>
                            <span class="weekly-weather-value">24</span>
                        </li>
                        <li>
                            Miércoles
                            <img src="assets/layout/images/dashboard/weather-icon-3.svg"/>
                            <span class="weekly-weather-value">19</span>
                        </li>
                        <li>
                            Jueves
                            <img src="assets/layout/images/dashboard/weather-icon-4.svg"/>
                            <span class="weekly-weather-value">15</span>
                        </li>
                        <li>
                            Viernes
                            <img src="assets/layout/images/dashboard/weather-icon-1.svg"/>
                            <span class="weekly-weather-value">24</span>
                        </li>
                        <li>
                            Sábado
                            <img src="assets/layout/images/dashboard/weather-icon-2.svg"/>
                            <span class="weekly-weather-value">21</span>
                        </li>
                        <li>
                            Domingo
                            <img src="assets/layout/images/dashboard/weather-icon-3.svg"/>
                            <span class="weekly-weather-value">20</span>
                        </li>
                    </ul>
                </div>
              </div>
            </p-scrollPanel>
        </div>
    `
})
export class AppRightpanelComponent implements AfterViewInit {

    @ViewChild('scrollRightPanel') rightPanelMenuScrollerViewChild: ScrollPanel;

    constructor(public app: AppMainComponent) {}

    ngAfterViewInit() {
      setTimeout(() => {this.rightPanelMenuScrollerViewChild.moveBar(); }, 100);
    }
}
