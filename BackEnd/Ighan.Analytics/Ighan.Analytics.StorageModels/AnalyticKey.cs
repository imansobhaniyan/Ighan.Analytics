using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ighan.Analytics.StorageModels
{
    public class AnalyticKey
    {
        public AnalyticKey()
        {
            Values = new List<AnalyticValue>();
        }

        public int Id { get; set; }

        public string Key { get; set; }

        public Project Project { get; set; }

        public List<AnalyticValue> Values { get; set; }
    }
}
