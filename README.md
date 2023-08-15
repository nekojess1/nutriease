# Nutriease

## Sumário

- [Visão Geral](#visão-geral)
- [Requisitos](#requisitos)
- [Configuração](#configuração)
- [Executando o Projeto](#executando-o-projeto)
- [Características do Projeto](#características-do-projeto)
  - [Arquitetura MVVM](#arquitetura-mvvm)
  - [Injeção de Dependência com Koin](#injeção-de-dependência-com-koin)
  - [Coroutines para Gerenciamento Assíncrono](#coroutines-para-gerenciamento-assíncrono)
- [Contato](#contato)
  
## Visão Geral

Nutriease é um aplicativo projetado para nutricionistas que desejam otimizar o atendimento e oferecer planos alimentares personalizados. Cadastre pacientes, gere dietas e receitas integradas ao GPT, proporcionando um acompanhamento nutricional eficiente e focado nos objetivos de saúde individuais.


## Requisitos

Antes de começar, certifique-se de ter os seguintes requisitos instalados em seu ambiente de desenvolvimento:

- Android Studio (versão recomendada: 4.0 ou superior)
- Kotlin

## Configuração

1. Clone este repositório em seu ambiente local:

<pre>
 git clone https://github.com/nekojess1/nutriease.git
</pre>

2. Abra o Android Studio.

3. Selecione "Open an existing Android Studio project" e navegue até o diretório clonado.

## Executando o Projeto

1. O projeto utiliza o Android Gradle Plugin versão 7.0. Certifique-se de que sua versão do Android Studio seja compatível.

2. Clique em "Sync Now" (ou "Sync Project with Gradle Files") no canto superior direito da tela para sincronizar as dependências do projeto.

3. Execute o aplicativo em um emulador ou dispositivo Android:

- Conecte um dispositivo Android via USB e ative a depuração USB no dispositivo.
- Selecione um emulador Android na barra de ferramentas AVD Manager do Android Studio.

4. Pressione o botão "Run" (ou Shift + F10) para compilar e executar o aplicativo.

## Definições do projeto 

### Arquitetura MVVM

O Nutriease adota a arquitetura MVVM (Model-View-ViewModel) para uma separação clara de responsabilidades e um desenvolvimento mais organizado. Os componentes principais da arquitetura são:

- **Model**: Lida com a lógica de negócios e acessa os dados.
- **View**: Exibe os dados e reage às interações do usuário.
- **ViewModel**: Mantém e gerencia os dados relacionados à UI.

### Injeção de Dependência com Koin

O projeto Nutriease utiliza o Koin para injeção de dependência. O Koin é um framework de injeção de dependência leve e pragmático para Kotlin.

### Coroutines

O projeto utiliza coroutines para lidar com chamadas assíncronas de maneira mais concisa e eficiente. Coroutines permitem que você escreva código assíncrono de forma sequencial.

## Contato

- Jéssica Alves de Souza: [GitHub](https://github.com/nekojess1)
- Steffane Ribeiro: [GitHub](https://github.com/steffaneribeiro)
