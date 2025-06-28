using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using InteligentServer.Models;
using InteligentServer.Service;
using Microsoft.AspNetCore.Mvc;
using Microsoft.VisualBasic;

namespace InteligentServer.Controller
{
    [ApiController]
    [Route("api/[controller]")]
    public class SensorDataController : ControllerBase
    {
        private readonly ISensorDataService _service;

        public SensorDataController(ISensorDataService service)
        {
            _service = service;
        }

        [HttpGet]
        public IActionResult GetAll()
        {
            var sensordata = _service.GetAllSensordata();
            return Ok(sensordata);
        }

        [HttpGet("{id}")]
        public IActionResult GetById(int id)
        {
            var sensordata = _service.GetSensordataById(id);
            return Ok(sensordata);
        }

        [HttpPost]
        public IActionResult Create(SensorData sensorData)
        {
            _service.CreateSensordata(sensorData);
            return Ok();
        }

        [HttpPut("{id}")]
        public IActionResult Update(int id, SensorData sensorData)
        {
            _service.UpdateSensordata(id, sensorData);
            return Ok();
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            _service.DeleteSensordata(id);
            return Ok();
        }
    }
}