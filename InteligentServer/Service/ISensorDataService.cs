using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using InteligentServer.Models;

namespace InteligentServer.Service
{
    public interface ISensorDataService
    {
        IEnumerable<SensorData> GetAllSensordata();
        SensorData GetSensordataById(int id);
        SensorData CreateSensordata(SensorData sensorData);
        SensorData UpdateSensordata(int id, SensorData sensorData);
        SensorData DeleteSensordata(int id);
    }
}