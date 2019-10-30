using AppOSWebApi.DAO;
using AppOSWebApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;

namespace AppOSWebApi.Controllers
{
    public class LoginApiController : ApiController
    {

        [HttpGet]
        [Route("api/LoginApiController/Entrar")]
        public async Task<PostApiResponse<Empresa>> EntrarAsync(String cpf_cnpj, String senha)
        {
            var retorno = new PostApiResponse<Empresa>();
            try
            {
                if (String.IsNullOrEmpty(cpf_cnpj))
                {
                    retorno.Result = false;
                    retorno.Mensagem = "CPF ou CNPJ não informado. ";
                    return retorno;
                }
                if (String.IsNullOrEmpty(senha))
                {
                    retorno.Result = false;
                    retorno.Mensagem = "Senha não informado. ";
                    return retorno;
                }

                Expression<Func<Empresa, bool>> filter = x => x.CPFCNPJ.Equals(cpf_cnpj) && x.Login.Senha.Equals(senha);
                var empresa = new EmpresaDAO().FindFirstBywhere(filter);

                if (empresa == null)
                {
                    retorno.Result = false;
                    retorno.Mensagem = "CNPJ ou a Senha não conferem. ";
                    return retorno;
                }
                else
                {

                    empresa.Login.Ultimo_Acesso = DateTime.Now;

                    retorno.Result = true;
                    retorno.Objeto = empresa;
                    retorno.Mensagem = "Logado com Sucesso.";


                    await new EmpresaDAO().Update(empresa);

                }

            }
            catch (Exception ex)
            {
                retorno.Result = false;
                retorno.Exception = ex.ToString();
            }

            return retorno;


        }



        [HttpPost]
        [Route("api/LoginApiController/EsqueciSenha")]
        public async Task<PostApiResponse<bool>> EsqueciSenhaAsync(EmpresaEsqueciSenha values)
        {
            var retorno = new PostApiResponse<bool>();

            try
            {
                Expression<Func<Empresa, bool>> filter = x =>
                                                x.CPFCNPJ.Equals(values.CPFCNPJ) &&
                                                x.Login.Usuario.Equals(values.NomeRazaoSocial) &&
                                                x.Login.Email.Equals(values.Email);



                var empresa = new EmpresaDAO().FindFirstBywhere(filter);
                if (empresa == null)
                {
                    retorno.Result = false;
                    retorno.Mensagem = "Dados não confere...";
                    return retorno;
                }
                else
                {
                    empresa.Login.Senha = "4pp05";
                    empresa.Data_Modificacao = DateTime.Now;
                    await new EmpresaDAO().Update(empresa);
                    retorno.Result = true;
                    retorno.Mensagem = "Senha Alterada com Sucesso... \n Senha Temporária - " + empresa.Login.Senha;
                }

            }
            catch (Exception ex)
            {
                retorno.Result = false;
                retorno.Exception = ex.ToString();
            }


            return retorno;
        }

    }
}