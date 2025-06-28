using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using InteligentServer.Models;
using InteligentServer.Repository;

namespace InteligentServer.Service
{
    public class SensorDataService : ISensorDataService
    {
        private ISensorDataRepository _repository;

        public SensorDataService(ISensorDataRepository repository)
        {
            _repository = repository;
        }

        public IEnumerable<SensorData> GetAllSensordata()
        {
            return _repository.GetAll();
        }

        public SensorData GetSensordataById(int id)
        {
            return _repository.GetById(id);
        }

        public SensorData CreateSensordata(SensorData sensorData)
        {
            return _repository.Create(sensorData);
        }

        public SensorData UpdateSensordata(int id, SensorData sensorData)
        {
            return _repository.Update(id, sensorData);
        }

        public SensorData DeleteSensordata(int id)
        {
            return _repository.Delete(id);
        }        
    }
}