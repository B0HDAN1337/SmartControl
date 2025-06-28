using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace InteligentServer.Models
{
    public class SensorData
    {
        public int Id { get; set; }
        public DateTime Timestamp { get; set; }
        public double Temperature { get; set; }
        public double Humidity { get; set; }
        public int Light { get; set; }
    }
}