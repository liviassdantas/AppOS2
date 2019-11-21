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
    public class EmpresaApiController : ApiController
    {
        [HttpPost]
        [Route("api/EmpresaApiController/Cadastrar")]
        public async Task<PostApiResponse<bool>> CadastrarEmpresaAsync(Empresa values)
        {
            var retorno = new PostApiResponse<bool>();
            try
            {
                Expression<Func<Empresa, bool>> filter = x => x.cpfcnpj.Equals(values.cpfcnpj);
                var EmpresaCadastradas = new EmpresaDAO().FindByWhere(filter);
                if (EmpresaCadastradas.Count() == 0)
                {
                    values.Data_Modificacao = DateTime.Now.ToString();
                    values.Data_Ultima_Atualizacao = DateTime.Now.ToString();

                    await new EmpresaDAO().Insert(values);
                    retorno.Result = true;
                    retorno.Mensagem = "Gravado Com Sucesso! ";
                }
                else
                {
                    retorno.Result = false;
                    retorno.Mensagem = "Empresa já Cadastrada";
                }

            }
            catch (Exception ex)
            {
                retorno.Result = false;
                retorno.Exception = ex.Message;

            }
            return retorno;
        }




        [HttpPost]
        [Route("api/EmpresaApiController/MudarSenha")]
        public async Task<PostApiResponse<bool>> MudarSenha(EmpresaSenha values)
        {
            var retorno = new PostApiResponse<bool>();
            try
            {
                Expression<Func<Empresa, bool>> filter = x => x.cpfcnpj.Equals(values.CPFCNPJ) && x.Login.Senha.Equals(values.Senha_Antiga);
                var empresa = new EmpresaDAO().FindFirstBywhere(filter);
                if (empresa == null)
                {
                    retorno.Result = false;
                    retorno.Mensagem = "Senha informada não Encontrada";

                }
                else
                {
                    empresa.Login.Senha = values.Senha_Nova;
                    empresa.Data_Modificacao = DateTime.Now.ToString();
                    empresa.Data_Ultima_Atualizacao = DateTime.Now.ToString();
                    await new EmpresaDAO().Update(empresa);

                    retorno.Result = true;
                    retorno.Mensagem = "Senha Alterada com sucesso";

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
        [Route("api/EmpresaApiController/AtualizarDados")]
        public async Task<PostApiResponse<Empresa>> AtualizarDados(Empresa values)
        {
            var retorno = new PostApiResponse<Empresa>();
            try
            {
                Expression<Func<Empresa, bool>> filter = x => x.cpfcnpj.Equals(values.cpfcnpj);
                var empresaCadastrada = new EmpresaDAO().FindFirstBywhere(filter);
                if (empresaCadastrada == null)
                {
                    retorno.Result = false;
                    retorno.Mensagem = "Dados informados incorretos(CPF ou CNPJ)";
                }
                else
                {
                    var empresaAtualizada = new Empresa()
                    {
                        _id = empresaCadastrada._id,
                        id = empresaCadastrada.id,
                        cpfcnpj = empresaCadastrada.cpfcnpj,
                        Data_Modificacao = DateTime.Now.ToString(),
                        Data_Ultima_Atualizacao = DateTime.Now.ToString(),
                        Email = values.Email,
                        Endereco = values.Endereco,
                        Login = values.Login,
                        Nome = values.Nome,
                        Telefone = values.Nome,


                    };

                    await new EmpresaDAO().Update(empresaAtualizada);
                    retorno.Result = true;
                    retorno.Mensagem = "Empresa Atualizada com sucesso";
                    retorno.Objeto = empresaAtualizada;
                }
            }
            catch (Exception ex)
            {
                retorno.Result = false;
                retorno.Exception = ex.Message;
            }
            return retorno; 
        }


    }

}