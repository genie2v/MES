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

            writer.WriteLine("action=get_qty");
            writer.Flush();
            getQty();
        }

        void getQty() 
        {
            //writer.WriteLine("get_qty");
            //writer.Flush();
            //MessageBox.Show("JOIN");

            String count = reader.ReadLine();
            String receive = "";

            for (int i = 0; i < Convert.ToInt16(count); i++)
            {
                receive = reader.ReadLine();
                //MessageBox.Show(receive);
                String[] qty = receive.Split(',');
                //foreach (String s in qty) MessageBox.Show(s);
                dataTable.Rows.Add(qty[0], qty[1], qty[2]);
                dataGridView1.DataSource = dataTable;
            }
        }

        private void dataGridView1_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            String clickOper = this.dataGridView1.CurrentRow.Cells["Oper"].Value.ToString();
            writer.WriteLine("action=get_lotlist;para1="+clickOper);
            //writer.WriteLine("action=get_lotlist");
            writer.Flush();
            fillLotlist();
        }

        void fillLotlist() 
        {
            //String clickOper = this.dataGridView1.CurrentRow.Cells["Oper"].Value.ToString();
            //writer.WriteLine(clickOper);
            //writer.Flush();

            String count = reader.ReadLine();
            //MessageBox.Show(count);
            String receive = "";

            dataTable2.Rows.Clear();
            dataGridView2.DataSource = dataTable;
            for (int i = 0; i < Convert.ToInt16(count); i++)
            {
                receive = reader.ReadLine();
                String[] lotlist = receive.Split(',');
                dataTable2.Rows.Add(lotlist[0], lotlist[1], lotlist[2], lotlist[3], lotlist[4]);
                dataGridView2.DataSource = dataTable2;
            }
        }
    }
}
