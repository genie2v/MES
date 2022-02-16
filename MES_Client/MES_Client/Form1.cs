using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MES_Client
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnCreateLot_Click(object sender, EventArgs e)
        {
            CreateLot createLot = new CreateLot();
            createLot.ShowDialog();
        }

        private void btnViewLotList_Click(object sender, EventArgs e)
        {
            ViewLotList viewLotList = new ViewLotList();
            viewLotList.ShowDialog();
        }

        private void btnViewLotHis_Click(object sender, EventArgs e)
        {
            ViewLotHistory viewLotHistory = new ViewLotHistory();
            viewLotHistory.ShowDialog();
        }
    }
}
