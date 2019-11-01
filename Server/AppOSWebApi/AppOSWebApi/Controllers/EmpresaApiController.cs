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
                Expression<Func<Empresa, bool>> filter = x => x.CPF_CNPJ.Equals(values.CPF_CNPJ);
                var EmpresaCadastradas = new EmpresaDAO().FindByWhere(filter);
                if (EmpresaCadastradas.Count() == 0)
                {
                    values.Data_Modificacao = DateTime.Now;
                    values.Data_Ultima_Atualizacao = DateTime.Now;

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
                Expression<Func<Empresa, bool>> filter = x => x.CPF_CNPJ.Equals(values.CPFCNPJ) && x.Login.Senha.Equals(values.Senha_Antiga);
                var empresa = new EmpresaDAO().FindFirstBywhere(filter);
                if (empresa == null)
                {
                    retorno.Result = false;
                    retorno.Mensagem = "Senha informada não Encontrada";

                }
                else
                {
                    empresa.Login.Senha = values.Senha_Nova;
                    empresa.Data_Modificacao = DateTime.Now;
                    empresa.Data_Ultima_Atualizacao = DateTime.Now;
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
                Expression<Func<Empresa, bool>> filter = x => x.CPF_CNPJ.Equals(values.CPF_CNPJ);
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
                        CPF_CNPJ = empresaCadastrada.CPF_CNPJ,
                        Data_Modificacao = DateTime.Now,
                        Data_Ultima_Atualizacao = DateTime.Now,
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