using System.Collections.Generic;

namespace Ighan.Analytics.StorageModels
{
    public class User
    {
        public int Id { get; set; }

        public string UserName { get; set; }

        public string Password { get; set; }

        public List<Project> Projects { get; set; }
    }
}
