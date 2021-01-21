import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-electricidad',
  templateUrl: './electricidad.component.html',
  styleUrls: ['./electricidad.component.css']
})
export class ElectricidadComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Asistencias/Electricidad', routerLink: ['/asistencia/electricidad']}
        ]);
    }

  ngOnInit() {
  }

}
