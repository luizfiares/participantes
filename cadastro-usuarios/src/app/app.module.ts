import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { AppRoutingModule } from './app-routing.module';  // Importando o AppRoutingModule
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UsuarioService } from './services/usuario.service';
import { ReactiveFormsModule } from '@angular/forms';
import { NgxMaskModule } from 'ngx-mask';
import { DatePipe } from '@angular/common';


@NgModule({
  declarations: [
    AppComponent,
    UsuariosComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,  
    ReactiveFormsModule,
    NgxMaskModule.forRoot(), 
  ],
  providers: [UsuarioService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }