using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MES_Client
{
    public partial class Form1 : Form
    {
        static TcpClient tcWip = new TcpClient("localhost", 8010);
        static TcpClient tcQuery = new TcpClient("localhost", 8000);

        public Form1()
        {
            InitializeComponent();

        }

        private void btnCreateLot_Click(object sender, EventArgs e)
        {
            // 전역변수로 wip query socket 연결 후 생성자로 팝업창 open
            CreateLot createLot = new CreateLot(tcWip, tcQuery);
             createLot.ShowDialog();
        }

        private void btnViewLotList_Click(object sender, EventArgs e)
        {
            ViewLotList viewLotList = new ViewLotList(tcWip, tcQuery);
            viewLotList.ShowDialog();
        }

        private void btnViewLotHis_Click(object sender, EventArgs e)
        {
            ViewLotHistory viewLotHistory = new ViewLotHistory(tcWip, tcQuery);
            viewLotHistory.ShowDialog();
        }
    }
}
