using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Ighan.Analytics.WebApi.Models.Statistics
{
    public class StatisticsModel
    {
        public string Token { get; set; }

        public string Key { get; set; }

        public string Value { get; set; }

        public string Brand { get; set; }

        public string Model { get; set; }

        public string Manufacturer { get; set; }

        public string Release { get; set; }

        public int SDKInt { get; set; }
    }
}
