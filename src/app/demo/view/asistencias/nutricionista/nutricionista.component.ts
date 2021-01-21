import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-nutricionista',
  templateUrl: './nutricionista.component.html',
  styleUrls: ['./nutricionista.component.css']
})
export class NutricionistaComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Asistencias/Nutricionista Telefónico', routerLink: ['/asistencia/nutricionistaTelefonico']}
        ]);
    }

  ngOnInit() {
  }

}
