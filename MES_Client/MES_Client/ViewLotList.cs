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
        DataTable dataTable = new DataTable();
        DataTable dataTable2 = new DataTable();

        TcpClient tcWip = null;
        NetworkStream wipNs = null;
        StreamWriter wipWriter = null;
        StreamReader wipReader = null;

        TcpClient tcQuery = null;
        NetworkStream queryNs = null;
        StreamWriter queryWriter = null;
        StreamReader queryReader = null;

        public ViewLotList()
        {
            InitializeComponent();
        }

        public ViewLotList(TcpClient wip, TcpClient query)
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

            tcQuery = query;
            tcWip = wip;

            wipNs = tcWip.GetStream();
            wipWriter = new StreamWriter(wipNs);
            wipReader = new StreamReader(wipNs);

            queryNs = tcQuery.GetStream();
            queryWriter = new StreamWriter(queryNs);
            queryReader = new StreamReader(queryNs);
        }

        private void ViewLotList_Load(object sender, EventArgs e)
        {
            queryWriter.WriteLine("action=get_qty");
            queryWriter.Flush();
            getQty();
        }

        void getQty() 
        {
            String count = queryReader.ReadLine();

            for (int i = 0; i < Convert.ToInt16(count); i++)
            {
                String receive = queryReader.ReadLine();
                //MessageBox.Show(receive);
                String[] qty = receive.Split(',');
                dataTable.Rows.Add(qty[0], qty[1], qty[2]);
                dataGridView1.DataSource = dataTable;
            }
        }

        private void dataGridView1_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            String clickOper = this.dataGridView1.CurrentRow.Cells["Oper"].Value.ToString();
            queryWriter.WriteLine("action=get_lotlist;oper=" + clickOper);
            queryWriter.Flush();
            fillLotlist();
        }

        void fillLotlist() 
        {
            String count = queryReader.ReadLine();

            dataTable2.Rows.Clear();
            dataGridView2.DataSource = dataTable;

            for (int i = 0; i < Convert.ToInt16(count); i++)
            {
                String receive = queryReader.ReadLine();
                String[] lotlist = receive.Split(',');
                dataTable2.Rows.Add(lotlist[0], lotlist[1], lotlist[2], lotlist[3], lotlist[4]);
                dataGridView2.DataSource = dataTable2;
            }
        }
    }
}
