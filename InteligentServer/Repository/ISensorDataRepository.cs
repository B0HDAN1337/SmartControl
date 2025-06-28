using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Threading.Tasks;
using InteligentServer.Models;

namespace InteligentServer.Repository
{
    public interface ISensorDataRepository
    {
        IEnumerable<SensorData> GetAll();
        SensorData GetById(int id);
        SensorData Create(SensorData sensorData);
        SensorData Update(int id, SensorData sensorData);
        SensorData Delete(int id);
    }
}