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
    public partial class ViewLotHistory : Form
    {
        TcpClient client = null;
        NetworkStream ns = null;
        StreamWriter writer = null;
        StreamReader reader = null;

        public ViewLotHistory()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            client = new TcpClient("localhost", 8000);
            if (client.Connected) MessageBox.Show("Server Connected.");
            ns = client.GetStream();
            writer = new StreamWriter(ns);
            reader = new StreamReader(ns);

            writer.WriteLine("get_his");
            writer.Flush();

            getHis();
        }

        void getHis() 
        {
            String lotId = textBoxSearch.Text.ToString();

            writer.WriteLine(lotId);
            writer.Flush();
            MessageBox.Show(lotId);
            

        }
    }
}
