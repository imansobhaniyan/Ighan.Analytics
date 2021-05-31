using System;

namespace Ighan.Analytics.StorageModels
{
    public class AnalyticValue
    {
        public int Id { get; set; }

        public string Manufacturer { get; set; }

        public string Brand { get; set; }

        public string Model { get; set; }

        public string Release { get; set; }

        public int SDK { get; set; }

        public string Value { get; set; }

        public DateTime CreateDate { get; set; }

        public AnalyticKey Key { get; set; }
    }
}
