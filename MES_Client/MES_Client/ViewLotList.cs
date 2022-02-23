using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MES_Client
{
    public partial class ViewLotList : Form
    {
        TcpClient client = null;
        NetworkStream ns = null;
        StreamWriter writer = null;
        StreamReader reader = null;
        DataTable dataTable = new DataTable();
        DataTable dataTable2 = new DataTable();

        public ViewLotList()
        {
            InitializeComponent();

            dataTable.Columns.Add("Oper", typeof(String));
            dataTable.Columns.Add("Lot Qty", typeof(String));
            dataTable.Columns.Add("Lot Prod Qty", typeof(String));

            dataGridView1.DataSource = dataTable;

            dataTable2.Columns.Add("LOT", typeof(String));
            dataTable2.Columns.Add("Oper", typeof(String));
            dataTable2.Columns.Add("Flow", typeof(String));
            dataTable2.Columns.Add("Prod", typeof(String));
            dataTable2.Columns.Add("Prod Qty", typeof(String));

            dataGridView2.DataSource = dataTable2;
        }

        private void ViewLotList_Load(object sender, EventArgs e)
        {
            client = new TcpClient("localhost", 8000);
            //if (client.Connected) MessageBox.Show("Server Connected.");
            ns = client.GetStream();
            writer = new StreamWriter(ns);
            reader = new StreamReader(ns);

            getQty();
        }

        void getQty() 
        {

        }

        private void dataGridView1_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            client = new TcpClient("localhost", 8000);
            //if (client.Connected) MessageBox.Show("Server Connected.");
            ns = client.GetStream();
            writer = new StreamWriter(ns);
            reader = new StreamReader(ns);

            writer.WriteLine("get_lotlist");
            writer.Flush();
            fillLotlist();
        }

        void fillLotlist() 
        {

        }
    }
}
