import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-plomeria',
  templateUrl: './plomeria.component.html',
  styleUrls: ['./plomeria.component.css']
})
export class PlomeriaComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Asistencias/Plomería', routerLink: ['/asistencia/plomeria']}
        ]);
    }


    ngOnInit() {
  }

}
