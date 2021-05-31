using System.Collections.Generic;

namespace Ighan.Analytics.StorageModels
{
    public class Project
    {
        public int Id { get; set; }

        public string Title { get; set; }

        public string Token { get; set; }

        public User User { get; set; }

        public List<AnalyticKey> Keys { get; set; }
    }
}
