using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using InteligentServer.Data;
using InteligentServer.Models;
using Microsoft.EntityFrameworkCore.Query.SqlExpressions;

namespace InteligentServer.Repository
{
    public class SensorDataRepository : ISensorDataRepository
    {
        private InteligentAppDbContext _context;
        public SensorDataRepository(InteligentAppDbContext context)
        {
            _context = context;
        }

         public IEnumerable<SensorData> GetAll()
        {
            return _context.SensorDatas;
        }

        public SensorData GetById(int id)
        {
            return _context.SensorDatas.Find(id);
        }

        public SensorData Create(SensorData sensorData)
        {
            _context.SensorDatas.Add(sensorData);
            _context.SaveChanges();
            return sensorData;
        }

        public SensorData Update(int id, SensorData sensorData)
        {
            var existSensorData = _context.SensorDatas.Find(id);

            existSensorData.Timestamp = sensorData.Timestamp;
            existSensorData.Temperature = sensorData.Temperature;
            existSensorData.Humidity = sensorData.Humidity;
            existSensorData.Light = sensorData.Light;

            _context.SaveChanges();

            return existSensorData;
        }

        public SensorData Delete(int id)
        {
            var deleteSensorData = _context.SensorDatas.Find(id);

            _context.Remove(deleteSensorData);
            _context.SaveChanges();

            return deleteSensorData;
        }
    }
}