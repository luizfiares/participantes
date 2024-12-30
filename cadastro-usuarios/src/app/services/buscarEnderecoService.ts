import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BuscarEnderecoService {

  private readonly cepUrl = 'https://viacep.com.br/ws';

  constructor(private http: HttpClient) { }

  buscarEnderecoPorCep(cep: string): Observable<any> {
    if (!/^[0-9]{8}$/.test(cep)) {
      return of({ erro: true, mensagem: 'CEP inválido!' });
    }

    return this.http.get<any>(`${this.cepUrl}/${cep}/json/`).pipe(
      catchError((error) => {
        console.error('Erro ao buscar o CEP:', error);
        return of({ erro: true, mensagem: 'Erro ao buscar o CEP!' });
      })
    );
  }
}
