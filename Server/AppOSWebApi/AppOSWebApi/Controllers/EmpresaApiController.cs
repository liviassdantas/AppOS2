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
        public async Task<PostApiResponse<bool>> CadastrarEmpresaAsync(EmpresaModels values)
        {
            var retorno = new PostApiResponse<bool>();
            try
            {
                Expression<Func<EmpresaModels, bool>> filter = x => x.CPFCNPJ.Equals(values.CPFCNPJ);
                var EmpresaCadastradas = new EmpresaDAO().FindByWhere(filter);
                if (EmpresaCadastradas.Count() == 0)
                {
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

    }
}