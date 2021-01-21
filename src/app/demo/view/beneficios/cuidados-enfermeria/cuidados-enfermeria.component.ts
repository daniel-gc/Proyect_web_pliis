import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../../breadcrumb.service';

@Component({
  selector: 'app-cuidados-enfermeria',
  templateUrl: './cuidados-enfermeria.component.html',
  styleUrls: ['./cuidados-enfermeria.component.css']
})
export class CuidadosEnfermeriaComponent implements OnInit {

    constructor(private breadcrumbService: BreadcrumbService) {
        this.breadcrumbService.setItems([
            {label: 'Beneficios/Cuidados de Enfermería', routerLink: ['/beneficios/cuidadosEnfermeria']}
        ]);
    }

  ngOnInit() {
  }

}
