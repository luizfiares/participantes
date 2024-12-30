import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';  
import { Usuario } from '../models/usuario.model';  
import { AreaDeAtuacao } from '../models/areaDeAtuacao.models';
import { NivelDeEscolaridade } from '../models/niveDelEscolaridade.models';
import { Estados } from '../models/estados.models';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiUrl = 'http://localhost:8080/api/usuarios'; 
  private apiUrlAreas = 'http://localhost:8080/api/areas';
  private apiUrlEscolaridade = 'http://localhost:8080/api/escolaridade';
  private apiUrlEstados = 'http://localhost:8080/api/estados';
  
  constructor(private http: HttpClient) { }

 cadastrarUsuario(usuario: Usuario): Observable<any> {
  return this.http.post<any>(this.apiUrl, usuario);  
}

atualizarUsuario(usuario: Usuario): Observable<any> {
  return this.http.put<any>(`${this.apiUrl}/${usuario.id}`, usuario);  
}

buscarUsuarios(filtro?: any): Observable<Usuario[]> {
  return this.http.post<Usuario[]>(`${this.apiUrl}/buscar`, filtro).pipe(
    tap((response: Usuario[]) => {
      console.log('Usuários encontrados:', response);
    }),
    catchError((error: any) => {
      console.error('Erro ao buscar usuários:', error);
      return throwError(() => new Error(error));
    })
  );
}

carregarEstados(): Observable<Estados[]> {
  return this.http.get<Estados[]>(this.apiUrlEstados);
}

carregarAreasDeAtuacao(): Observable<AreaDeAtuacao[]> {
  return this.http.get<AreaDeAtuacao[]>(this.apiUrlAreas);
}

carregarNiveilEscolaridade(): Observable<NivelDeEscolaridade[]> {
  return this.http.get<NivelDeEscolaridade[]>(this.apiUrlEscolaridade);
}

excluirUsuario(id: number): Observable<void> {
 return this.http.delete<void>(`${this.apiUrl}/${id}`)
  .pipe(catchError(this.handleError));  
}

private handleError(error: any): Observable<never> {
 console.error('Erro na requisição:', error);
  return throwError(() => new Error('Ocorreu um erro na requisição. Tente novamente.'));
 }
}