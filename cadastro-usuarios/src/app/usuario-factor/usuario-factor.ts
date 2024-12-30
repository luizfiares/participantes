import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { cpfValidator } from '../validators/cpf-validators';
import { cpfAsyncValidator } from '../validators/cpf-async-validators';


export function createUsuarioForm(fb: FormBuilder): FormGroup {
  return fb.group({
    id: ['', Validators.required],
    nome: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    celular: ['', Validators.required],
    cpf: ['', [Validators.required, cpfValidator], [cpfAsyncValidator]],
    genero: ['Masculino', Validators.required],
    dataNascimento: ['', Validators.required],
    ativo: [true, Validators.required],
    curriculo: [null],
    areaDeAtuacao: [null, Validators.required],
    nivelDeEscolaridade: [null, Validators.required],
    estado: [null, Validators.required],
    enderecoParticipante: fb.group({
      cep: ['', [Validators.required, Validators.pattern(/^[0-9]{8}$/)]],
      endereco: [''],
      numero: [''],
      complemento: [''],
      bairro: [''],
      uf: [''],
      municipio: ['']
    })
  });
}
