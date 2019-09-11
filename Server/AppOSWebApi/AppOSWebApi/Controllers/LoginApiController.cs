using AppOSWebApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;

namespace AppOSWebApi.Controllers
{
    public class LoginApiController :ApiController
    {

        [HttpGet]
        [Route("api/LoginApiController/Entrar")]
        public PostApiController<EmpresaModels> Entrar(EmpresaLoginModels values)
        {
            return new PostApiController<EmpresaModels> {
                Mensagem = "Logado Com Sucesso",
                Result = true,
                Objeto = new EmpresaModels() {
                    CPFCNPJ = "teste",
                    Data_Ultima_Atualizacao = DateTime.Now,
                    Endereco = new EnderecoModels(),
                    Telefone = "000000"
                                     
                }
                           
            }; 
        }

    }
}