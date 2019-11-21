using AppOSWebApi.DAO;
using AppOSWebApi.Models;
using AppOSWebApi.Persistence.DAO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;

namespace AppOSWebApi.Controllers
{
    public class ClienteApiController:ApiController
    {


        [HttpGet]
        [Route("api/ClienteApiController/GetCliente")]
        public async Task<PostApiResponse<IEnumerable<ClienteModels>>> GetCliente(String busca)
        {
            var retorno = new PostApiResponse<IEnumerable<ClienteModels>>();
            try
            {
                Expression<Func<ClienteModels, bool>> filter = x => x.cpf_cnpj == busca|| x.nome == busca;
                var lista = new ClienteDAO().FindByWhere(filter);

                if (lista == null)
                {
                    retorno.Result = false;
                }
                else
                {
                    
                    retorno.Result = true;
                    retorno.Objeto = lista;

                }

            }
            catch (Exception ex)
            {
                retorno.Result = false;
                retorno.Exception = ex.Message;
            }
            return retorno;
        }



        //[HttpPost]
        //[Route("api/ClienteApiController/Cadastrar")]
        //public async Task<PostApiResponse<ClienteModels>> CadastrarClientes(ClienteModels values)
        //{
        //    var retorno = new PostApiResponse<ClienteModels>();

        //    try
        //    {

        //        if (String.IsNullOrEmpty(values.cpf_cnpj))
        //        {
        //            retorno.Result = false;
        //            retorno.Mensagem = "CPF OU CNPJ em branco.";
        //        }

        //        else
        //        {

        //            Expression<Func<ClienteModels, bool>> filter = x => x.cpf_cnpj == values.cpf_cnpj;
        //            var cliente = new ClienteDAO().FindFirstBywhere(filter);

        //            if (cliente != null)
        //            {
        //                retorno.Result = false;
        //                retorno.Mensagem = "Cliente Já cadastrado";

        //            }
        //            else
        //            {
        //                values.Data_Modificacao = DateTime.Now.ToString();
        //                new ClienteDAO().Insert(values);

        //                retorno.Result = true;
        //                retorno.Mensagem = "Cliente cadastrado com sucesso";
        //                retorno.Objeto = new ClienteDAO().FindFirstBywhere(filter);
                        

        //            }
        //        }
        //    }
        //    catch (Exception ex)
        //    {
        //        retorno.Result = false;
        //        retorno.Exception = ex.ToString();
        //    }

        //    return retorno;
        //}

    }
}