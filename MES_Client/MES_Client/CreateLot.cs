using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MES_Client
{
    public partial class CreateLot : Form
    {
        TcpClient client = null;
        NetworkStream ns = null;
        StreamWriter writer = null;
        StreamReader reader = null;

        public CreateLot()
        {
            InitializeComponent();
            this.ActiveControl = textBoxLotId;
        }

        private void CreateLot_Load(object sender, EventArgs e)
        {
            client = new TcpClient("localhost", 8000);
            if (client.Connected) MessageBox.Show("Server Connected.");
            ns = client.GetStream();
            writer = new StreamWriter(ns);
            reader = new StreamReader(ns);
        }

        private void textBoxLotId_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!(Char.IsLetter(e.KeyChar)) && !(Char.IsDigit(e.KeyChar)) && e.KeyChar != 8) 
            {
                e.Handled = true;
            }
        }

        private void comboBoxOper_DropDown(object sender, EventArgs e)
        {
            String selOper = labelOper.Text.ToString().ToLower();
            writer.WriteLine(selOper);
            writer.Flush();
            MessageBox.Show(selOper + "를 전송");
            //MessageBox.Show(selOper);
            //comboBoxOper.Items.Add("A");
        }

        private void btnReset_Click(object sender, EventArgs e)
        {
            textBoxLotId.Text = String.Empty;
            textBoxProdQty.Text = String.Empty;
        }

        private void btnCreate_Click(object sender, EventArgs e)
        {
        }
    }
}
