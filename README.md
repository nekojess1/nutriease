# Nutriease

## Sumário

- [Visão Geral](#visão-geral)
- [Requisitos](#requisitos)
- [Configuração](#configuração)
- [Executando o Projeto](#executando-o-projeto)
- [Definições do Projeto](#definições-do-projeto)
  - [Arquitetura MVVM](#arquitetura-mvvm)
  - [Injeção de Dependência com Koin](#koin)
  - [Coroutines](#coroutines)
  - [Firebase](#firebase)
  - [Integração com o GPT](#integração-com-o-gpt)
  - [Retrofit](#retrofit)


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

4. Acesse o Colab fornecido para a API: [Link Colab](https://colab.research.google.com/drive/1Wt3uPN54dSW0hFoqIs9LWTWZc7eveAxa?usp=sharing).

    O Colab contém o código necessário para executar a API usando o Flask e integrar o GPT ao Nutriease.
    
    ![image](https://github.com/nekojess1/nutriease/assets/45262259/b5a594b8-e343-47b2-a66e-c7906b58d852)
   
6. Antes de executar o projeto Android, verifique se você executou o Colab para configurar e iniciar a API.
   
8. No Android Studio, abra o arquivo RecipeAPI.kt, que é responsável pela configuração do Retrofit para realizar chamadas de API.

9. Na função função retrofit, atualize a URL para a URL temporária gerada pelo Flask-Ngrok no Colab. Por exemplo:
        
    ```kotlin
    fun retrofit(
        okHttpClient: OkHttpClient,
        url: String = "https://3224-35-245-71-171.ngrok.io"
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .build()
    }
    ```
#### :warning: trocar de http para https na URL da API 

11. Pressione o botão "Run" (ou Shift + F10) para compilar e executar o aplicativo.

### Observação sobre a URL da API

A URL gerada pelo Flask-Ngrok para a API pode variar a cada vez que você a executa. Isso significa que a URL fornecida para o Nutriease se comunicar com a API pode precisar ser atualizada em seu código sempre que você iniciar a API novamente. Certifique-se de verificar e ajustar a URL conforme necessário para garantir a integração adequada entre o Nutriease e a API.

## Definições do projeto 

### `Arquitetura MVVM`

O Nutriease adota a arquitetura MVVM (Model-View-ViewModel) para uma separação clara de responsabilidades e um desenvolvimento mais organizado. Os componentes principais da arquitetura são:

- **Model**: Lida com a lógica de negócios e acessa os dados.
- **View**: Exibe os dados e reage às interações do usuário.
- **ViewModel**: Mantém e gerencia os dados relacionados à UI.

### `Koin`

O projeto Nutriease utiliza o Koin para injeção de dependência. O Koin é um framework de injeção de dependência leve e pragmático para Kotlin.

### `Coroutines`

O projeto utiliza coroutines para lidar com chamadas assíncronas de maneira mais concisa e eficiente. Coroutines permitem que você escreva código assíncrono de forma sequencial.

### `Firebase`

O Nutriease faz uso do Firebase Firestore para armazenamento de dados em tempo real e do Firebase Authenticator para autenticação segura de usuários. Certifique-se de configurar corretamente o Firebase em sua conta e atualizar as configurações no projeto.

### `Integração com o GPT`

O Nutriease oferece integração com o GPT para geração de conteúdo textual personalizado. Configure a API do GPT em sua conta e atualize as configurações no projeto para aproveitar esse recurso.

### `Retrofit`

O projeto utiliza o Retrofit para realizar chamadas de API.


## Contato

- Jéssica Alves de Souza: [GitHub](https://github.com/nekojess1)
- Steffane Ribeiro: [GitHub](https://github.com/steffaneribeiro)
