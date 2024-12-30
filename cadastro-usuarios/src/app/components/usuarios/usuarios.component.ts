import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsuarioService } from 'src/app/services/usuario.service'; 
import { Usuario } from 'src/app/models/usuario.model'; 
import * as bootstrap from 'bootstrap';
import Swal from 'sweetalert2';
import { AreaDeAtuacao } from 'src/app/models/areaDeAtuacao.models';
import { NivelDeEscolaridade } from 'src/app/models/niveDelEscolaridade.models';
import { Estados } from 'src/app/models/estados.models';
import { EnderecoParticipante } from 'src/app/models/enderecoParticipante.models';
import { HttpClient } from '@angular/common/http';
import { jsPDF } from 'jspdf';
import autoTable from 'jspdf-autotable';
import { cpfValidator } from 'src/app/validators/cpf-validators';
import { cpfAsyncValidator } from 'src/app/validators/cpf-async-validators';
import { RelatorioService } from 'src/app/services/relatorio.service';
import { BuscarEnderecoService } from 'src/app/services/buscarEnderecoService';
import { createUsuarioForm } from 'src/app/usuario-factor/usuario-factor';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

  tituloModal: string = ''; 
  usuarioForm!: FormGroup;
  participantes: Usuario[] = []; 
  filtro: any = {};
  areaDeAtuacao: AreaDeAtuacao[] = [];
  nivelDeEscolaridade: NivelDeEscolaridade[] = [];
  estados: Estados[] = [];
  endereco: EnderecoParticipante [] = [];
  notificacaoErro: string | null = null;
  

  constructor(
    private usuarioService: UsuarioService, 
    private fb: FormBuilder,
    private http: HttpClient,
    private relatorioService: RelatorioService,
    private buscarEnderecoService: BuscarEnderecoService) { 

    // this.usuarioForm = this.fb.group({
    //   id: ['', Validators.required],
    //   nome: ['', Validators.required],
    //   email: ['', [Validators.required, Validators.email]],
    //   celular: ['', Validators.required],
    //   cpf: ['',[ Validators.required, cpfValidator], [cpfAsyncValidator]], 
    //   genero: ['Masculino', Validators.required],
    //   dataNascimento: ['', Validators.required],
    //   ativo: [true, Validators.required],
    //   curriculo: [null],
    //   areaDeAtuacao: [null, Validators.required],
    //   nivelDeEscolaridade: [null, Validators.required],
    //   estado: [null, Validators.required],
    //   enderecoParticipante: this.fb.group({
    //     cep: ['', [Validators.required, Validators.pattern(/^[0-9]{8}$/)]],
    //     endereco: [''],
    //     numero: [''],
    //     complemento: [''],
    //     bairro: [''],
    //     uf: [''],
    //     municipio: ['']
    //   })
    // }); 
  }

  ngOnInit(): void {
    this.usuarioForm = createUsuarioForm(this.fb);
    this.carregarParticipantes();
    this.carregarAreasDeAtuacao();
    this.carregarNivelEscolaridade();
    this.carregarEstados();
  }

  carregarEstados(): void {
    this.usuarioService.carregarEstados().subscribe(
      (estados) => {
        this.estados = estados;
      },
      (error) => {}
    );
  }

  carregarAreasDeAtuacao(): void {
    this.usuarioService.carregarAreasDeAtuacao().subscribe(
      (areas) => {
        this.areaDeAtuacao = areas;
      },
      (error) => {}
    );
  }

  carregarNivelEscolaridade(): void {
    this.usuarioService.carregarNiveilEscolaridade().subscribe(
      (escolaridade) => {
        this.nivelDeEscolaridade = escolaridade;
      },
      (error) => {}
    );
  }
  
  cadastrarUsuario(): void {
      const usuario: Usuario = this.usuarioForm.value;
      if (usuario.id) {
        this.usuarioService.atualizarUsuario(usuario).subscribe(
          (response) => {
            this.carregarParticipantes(); 
            this.fecharModal();
          },
        );
      } else {
        this.usuarioService.cadastrarUsuario(usuario).subscribe(
          (response) => {
            this.carregarParticipantes(); 
            this.fecharModal();
          },
        );
      }
  }

  fecharModal() {
    const modalElement = document.getElementById('cadastroModal');
    if (modalElement) {
      const modalInstance = bootstrap.Modal.getInstance(modalElement);
      modalInstance?.hide(); 
    }
  }

  carregarParticipantes() {
    this.usuarioService.buscarUsuarios(this.filtro).subscribe(    
      (usuarios) => {
        this.participantes = usuarios;
      },
      (error) => console.error('Erro ao carregar participantes:', error)
    );
  }

  abrirModal(participante?: Usuario): void {
    if (participante) {
      this.tituloModal = 'Atualizar Participante';
      this.usuarioForm.patchValue({
        ...participante,
        estado: this.estados.find(estado => estado.id === participante.estado?.id),
        areaDeAtuacao: this.areaDeAtuacao.find(area => area.id === participante.areaDeAtuacao?.id),
        nivelDeEscolaridade: this.nivelDeEscolaridade.find(nivel => nivel.id === participante.nivelDeEscolaridade?.id),
      });
    } else {
      this.tituloModal = 'Incluir Participante';
      this.usuarioForm.reset();
    }
  }
  

  excluirUsuario(id: number | undefined): void {
    if (id !== undefined) {
      Swal.fire({
        title: 'Confirmação',
        text: 'Deseja realmente excluir este participante?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sim, excluir',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          this.usuarioService.excluirUsuario(id).subscribe(
            (response) => {
              this.carregarParticipantes();
            },
            (error) => {
              console.error('Erro ao excluir o usuário', error);
              Swal.fire('Erro!', 'Houve um erro ao tentar excluir o participante.', 'error');
            }
          );
        }
      });
    } else {
      Swal.fire('Erro!', 'ID do usuário não encontrado!', 'error');
    }
  }

  buscarEndereco(event: Event): void {
    const cep = (event.target as HTMLInputElement).value;
    const enderecoForm = this.usuarioForm.get('enderecoParticipante');
  
    this.buscarEnderecoService.buscarEnderecoPorCep(cep).subscribe((dados) => {
      if (dados && !dados.erro) {
        enderecoForm?.patchValue({
          endereco: dados.logradouro,
          bairro: dados.bairro,
          uf: dados.uf,
          municipio: dados.localidade,
        });
        enderecoForm?.get('cep')?.setErrors(null);
      } else {
        enderecoForm?.get('cep')?.setErrors({ customError: dados.mensagem });
        enderecoForm?.patchValue({
          endereco: '',
          bairro: '',
          uf: '',
          municipio: '',
        });
      }
    });
  }
  


  imprimirRelatorioPdf() {
    this.relatorioService.imprimirRelatorioPdf(this.participantes);
  }


  imprimirRelatorioXls() {
    this.relatorioService.imprimirRelatorioXls(this.participantes);
  }

  onBlur(controlName: string) {
    const control = this.usuarioForm.get(controlName);
    if (control) {
      control.markAsTouched(); 
      control.updateValueAndValidity();
    }
  }

  limparFiltros(): void {
    window.location.reload(); 
  }

}


