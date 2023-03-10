#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "fila2.h"



static int incr(int i){
	return (i+1)%N;
}

Fila2 *fila_cria_vet(){
	Fila2 *f = (Fila2*) malloc(sizeof(Fila2));
	f->n = 0;
	f->ini = 0;
	return f;
}

void fila_insere_vet(Fila2 *f, float v){
	int fim;
	if(f->n==N){
		printf("Capacidade estourou\n");
		return;
	}
	fim=(f->ini + f->n) % N;
	f->vet[fim]=v;
	f->n++;
}

void fila_insere_ini_vet(Fila2*f, float v){
	int prec;
	if(f->n==N){
		printf("Capacidade estourou\n");
		return;
	}

	prec=(f->ini-1 + N)%N;
	f->vet[prec]=v;
	f->ini=prec;
	f->n++;
}

float fila_retira_fim_vet(Fila2 *f){
	int ult;
	float v;
	if(fila_vazia_vet(f)){
		printf("Fila vazia\n");
		exit(1);
	}
	ult=(f->ini + f->n -1) % N;
	v=f->vet[ult];
	f->n--;
	return v;
}

float fila_retira_vet(Fila2*f){
	float v;
	if(fila_vazia_vet(f)){
		printf("Fila2vazia!\n");
		exit(1);
	}
	v=f->vet[f->ini];
	f->ini = (f->ini + 1) % N;
	f->n--;
	return v;
}

int fila_vazia_vet(Fila2*f){
	return (f->n == 0);
}

void fila_libera_vet(Fila2*f){
	free(f);
}


// Lista encadeada
FilaL2*fila_cria_l(){
	FilaL2*f = (FilaL2*) malloc(sizeof(FilaL2));
	f->ini = f->fim = NULL;
	return f;
}

void fila_insere_l(FilaL2*f,char *v){
	Lista *n = (Lista *) malloc(sizeof(Lista));
	int i;
	for(i=0;i<20;i++) n->info[i]=v[i];
	n->prox = NULL;
	if(f->fim != NULL) f->fim->prox = n;
	else f->ini = n;
	f->fim = n;
}

void fila_insere_ini_l(FilaL2 *fila, char *v){
	Lista *l=(Lista *)malloc(sizeof(Lista));
	strcpy(l->info, v);
	l->prox=fila->ini;
	fila->ini=l;
}

char *fila_retira_l(FilaL2*f){
	Lista *t;
	char *v;
	if(fila_vazia_l(f)){
		printf("Fila2 vazia!");
		exit(1);
	}
	t = f->ini;
	v = t->info;
	f->ini = t->prox;
	if(f->ini==NULL) f->fim = NULL;
	free(t);
	return v;
}

char *fila_retira_fim_l(FilaL2 *fila){
	Lista *t=fila->ini;
	Lista *ant;
	char *v;
	if(fila_vazia_l(fila)){
		printf("Fila2 vazia!");
		exit(1);
	}
	while(t!=fila->fim){
		ant=t;
		t=t->prox;
	}
	v=t->info;
	if(ant!=NULL) ant->prox=NULL;
	fila->fim=ant;
	free(t);
	return v;
}

int fila_vazia_l(FilaL2*f){
	return (f->ini == NULL);
}

void fila_libera_l(FilaL2*f){
	Lista *q = f->ini;
	while(q!=NULL){
		Lista *t = q->prox;
		free(q);
		q = t;
	}
	free(f);
}

Lista* fila_busca_l(FilaL2*fila, char informacao[]){
	Lista *q;
	for(q=fila->ini; q!=NULL; q=q->prox){
		if(strcmp(informacao, q->info)==0){
			return q;
		}
	}
	return NULL;
}

// Funções de impressão

void fila_imprime_vet(Fila2*f){
	int i;
	for(i=0;i<f->n; i++) printf("%f \n", f->vet[(f->ini+i)%N]);
}

void fila_imprime_l(FilaL2*f){
	Lista *q;
	for(q=f->ini; q!= NULL; q=q->prox) printf("\t%s\n",q->info);
}
