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
    public class OrdemServicoApiController : ApiController
    {


        [HttpPost]
        [Route("api/OrdemServicoApiController/Cadastrar")]
        public async Task<PostApiResponse<bool>> Cadastrar(OrdemServicoCadastro values)
        {
            var retorno = new PostApiResponse<bool>();

            try
            {


                Expression<Func<Empresa,bool>> filter = x => x.CPFCNPJ == values.CPFCNPJ_Empresa;
                var empresa = new EmpresaDAO().FindFirstBywhere(filter);
                
                await new Ordem_ServicoDAO().InsertSequence(values,empresa);

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