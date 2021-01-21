import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-pediatra',
  templateUrl: './pediatra.component.html',
  styleUrls: ['./pediatra.component.css']
})
export class PediatraComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Asistencias/Pediatra Telefónico', routerLink: ['/asistencia/pediatraTelefonico']}
        ]);
    }


    ngOnInit() {
  }

}
