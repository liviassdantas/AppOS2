using AppOSWebApi.Core;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using System.Web;

namespace AppOSWebApi.Persistence.DAO
{
    public abstract class BaseDAO<T> : IDisposable where T : BaseModel
    {
        public async Task Insert(T obj)
        {
            try
            {
                IMongoDatabase banco = DataSource.GetConnection();
                IMongoCollection<T> collection = banco.GetCollection<T>(obj.GetType().Name);

                await collection.InsertOneAsync(obj);
            }
            catch (Exception ex)
            {
                throw new Exception("Erro: " + ex.ToString());
            }
        }

        public async Task Update(T obj)
        {
            //try
            //{
            IMongoDatabase banco = DataSource.GetConnection();
            IMongoCollection<T> collection = banco.GetCollection<T>(obj.GetType().Name);

            var consulta = Builders<T>.Filter.Eq("_id", obj._id);
            await collection.ReplaceOneAsync(consulta, obj);

            //}
            //catch (Exception ex)
            //{
            //throw new Exception("Erro: " + ex.ToString());
            //}
        }

        public async Task Delete(T obj)
        {
            try
            {
                IMongoDatabase banco = DataSource.GetConnection();
                IMongoCollection<T> collection = banco.GetCollection<T>(obj.GetType().Name);

                Expression<Func<T, bool>> filter = x => x._id == obj._id;
                await collection.DeleteOneAsync<T>(filter);
            }
            catch (Exception ex)
            {
                throw new Exception("Erro: " + ex.ToString());
            }
        }


        public T FindFirstBywhere(Expression<Func<T, bool>> where)
        {
            try
            {
                IMongoDatabase banco = DataSource.GetConnection();
                IMongoCollection<T> collection = banco.GetCollection<T>(typeof(T).Name);

                return collection.FindAsync(where).Result.ToList().FirstOrDefault();

            }
            catch (Exception ex)
            {
                throw new Exception("Erro: " + ex.ToString());
            }
        }

        public List<T> FindByWhere(Expression<Func<T, bool>> where)
        {
            try
            {
                IMongoDatabase banco = DataSource.GetConnection();
                IMongoCollection<T> collection = banco.GetCollection<T>(typeof(T).Name);
                return collection.FindAsync(where).Result.ToList();
            }
            catch (Exception ex)
            {
                throw new Exception("Erro :" + ex.ToString());
            }
        }

        #region Disposable
        bool disposed = false;

        // método privado para controle
        // da liberação dos recursos
        private void Dispose(bool disposing)
        {
            // Verifique se Dispose já foi chamado.
            if (!this.disposed)
            {
                if (disposing)
                {
                    // Liberando recursos gerenciados

                }

                // Seta a variável booleana para true,
                // indicando que os recursos já foram liberados
                disposed = true;
            }
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
        #endregion
    }
}